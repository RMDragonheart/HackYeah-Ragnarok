package com.vikings.hackaton.demo.gui;

import com.google.common.base.Joiner;
import com.vikings.hackaton.demo.converter.XYData;
import com.vikings.hackaton.demo.reader.EventsReader;
import com.vikings.hackaton.demo.service.GeoPositionRetrievalService;
import javafx.scene.media.MediaPlayer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.newLinkedList;

/**
 * @author lukaszgrabski
 */
public class DataGenerateForm extends JPanel {

  private final JButton actionButton;
  private final File dataInputFile;
  private final GeoPositionRetrievalService geoPositionRetrievalService;
  private final CSVPrinter finePrinter;
  private final CSVPrinter wrongPrinter;
  private final File fineFile;
  private final File wrongFile;
  private String[] headers;
  private java.util.List<String> headersInOrder;
  private AtomicInteger notMatched = new AtomicInteger(0);
  private AtomicInteger strangeHouseNumber = new AtomicInteger(0);

  private Date startDate;
  private Date endDate;

  public DataGenerateForm(File dataInputFile, GeoPositionRetrievalService geoPositionRetrievalService) throws IOException {
    this.dataInputFile = dataInputFile;
    this.geoPositionRetrievalService = geoPositionRetrievalService;
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    readDataHeader(dataInputFile);

    finePrinter = new CSVPrinter(new FileWriter(fineFile = createTempFile("fine")), CSVFormat.newFormat(';').withRecordSeparator('\n'));
    wrongPrinter = new CSVPrinter(new FileWriter(wrongFile = createTempFile("wrong")), CSVFormat.newFormat(';').withRecordSeparator('\n'));

    add(new DataColumnsPanel(headers, headers -> headersInOrder = headers));

    add(actionButton = new JButton("DO IT!"));
    actionButton.addActionListener(e -> {
      SwingWorker worker = new SwingWorker() {

        @Override
        protected Object doInBackground() throws Exception {
          startDate = new Date();
          new EventsReader(new FileInputStream(dataInputFile), headers, headersInOrder).parseEvents(record -> {
            try {
              processRecord(record);
            } catch (IOException e1) {
              e1.printStackTrace();
            }
          });

          endDate = new Date();

          playSound();
          JOptionPane.showMessageDialog(DataGenerateForm.this, summaryText(), "Processing done!", JOptionPane.INFORMATION_MESSAGE);

          Files.write(logFile().toPath(), newArrayList(summaryText()));

          finePrinter.flush();
          wrongPrinter.flush();

          finePrinter.close();
          wrongPrinter.close();
          return null;
        }

        private File logFile() {
          return new File("logfile.log");
        }

        private String summaryText() {
          SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd  HH:mm:ss z");

          StringBuffer buffer = new StringBuffer();
          buffer.append(String.format("Start date: %s \n", dateFormat.format(startDate)));

          buffer.append(String.format("Not matched count: %s \n", notMatched.get()));
          buffer.append(String.format("Strange house number count: %s \n", strangeHouseNumber.get()));
          buffer.append(String.format("Fine file location: %s \n", fineFile.getAbsolutePath()));
          buffer.append(String.format("Wrong file location: %s \n", wrongFile.getAbsolutePath()));

          buffer.append(String.format("End date: %s \n", dateFormat.format(endDate)));

          buffer.append(String.format("Duration: %s ms\n", endDate.getTime()-startDate.getTime()));

          return buffer.toString();
        }
      };
      worker.execute();
    });
  }

  public void playSound() {
    try {
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("thunder.wav").getAbsoluteFile());
      Clip clip = AudioSystem.getClip();
      clip.open(audioInputStream);
      clip.start();
    } catch(Exception ex) {
      System.out.println("Error with playing sound.");
      ex.printStackTrace();
    }
  }

  private void processRecord(CSVRecord record) throws IOException {

    java.util.List<String> steetNameElements = newLinkedList();

    headersInOrder.forEach(headerInOrder -> {
      String partialValue = record.get(headerInOrder);
      steetNameElements.add(partialValue);
    });

    String address = normalize(Joiner.on(" ").join(steetNameElements));
    String[] addressParts = address.split(" ");
    String streetPart = Joiner.on(" ").join(Arrays.copyOf(addressParts, addressParts.length - 1));
    String houseNumberPart = addressParts[addressParts.length - 1];

    Optional<XYData> xyDataOptional = geoPositionRetrievalService.getXYData(streetPart, houseNumberPart);
    if (xyDataOptional.isPresent()) {
      appendRecord(finePrinter, record, xyDataOptional.get());
    } else {

      String houseNumberNoChars = houseNumberPart.replaceAll("[^0-9]", "");
      if ("".equals(houseNumberNoChars)) {
        strangeHouseNumber.incrementAndGet();
        return;
      }

      xyDataOptional = geoPositionRetrievalService.getXYData(streetPart, houseNumberNoChars);
      if (xyDataOptional.isPresent()) {
        appendRecord(finePrinter, record, xyDataOptional.get());
      } else {
        int houseNumberInt = Integer.parseInt(houseNumberNoChars);
        xyDataOptional = tryFindNearestHouse(streetPart, houseNumberInt, 10);
        if (xyDataOptional.isPresent()) {
          appendRecord(finePrinter, record, xyDataOptional.get());
        } else {
          xyDataOptional = lastChanceCheck(streetPart, houseNumberNoChars);
          if (xyDataOptional.isPresent()) {
            appendRecord(finePrinter, record, xyDataOptional.get());
          } else {
            notMatched.incrementAndGet();
            appendWrongRecord(wrongPrinter, record);
          }
        }
      }
    }
  }

  private File createTempFile(String prefix) throws IOException {
    return new File(prefix +".csv");
  }

  private void appendRecord(CSVPrinter finePrinter, CSVRecord record, XYData xyData) throws IOException {
    List<String> elements = newLinkedList();
    for (int i = 0; i < record.size(); i++) {
      elements.add(record.get(i));
    }
    elements.add(xyData.getX());
    elements.add(xyData.getY());

    finePrinter.printRecord(elements);
  }

  private void appendWrongRecord(CSVPrinter wrongPrinter, CSVRecord record) throws IOException {
    List<String> elements = newLinkedList();
    for (int i = 0; i < record.size(); i++) {
      elements.add(record.get(i));
    }
    wrongPrinter.printRecord(elements);
  }

  private Optional<XYData> lastChanceCheck(String street, String houseNumber) {
    String[] streetParts = street.split(" ");
    String streetLastPart = streetParts[streetParts.length - 1];
    return geoPositionRetrievalService.getXYDataPartial(streetLastPart, houseNumber);
  }

  private Optional<XYData> tryFindNearestHouse(String streetPart, int houseNumberInt, int numbersToGo) {
    if (numbersToGo == 0) {
      return Optional.empty();
    }
    Optional<XYData> xyDataOptional = geoPositionRetrievalService.getXYData(streetPart, "" + (houseNumberInt + 1));
    if (xyDataOptional.isPresent()) {
      return xyDataOptional;
    }
    return tryFindNearestHouse(streetPart, houseNumberInt + 1, numbersToGo - 1);
  }

  private String normalize(String street) {
    return street.trim().toLowerCase()
            .replaceAll("ul. ", "")
            .replaceAll("ul ", "")
            .replaceAll("ulica ", "")
            .replaceAll("u. ", "")
            .replaceAll("os. ", "osiedle ")
            .replaceAll("os ", "osiedle ")
            .replaceAll("al. ", "aleja ")
            .replaceAll("al ", "aleja ")
            .replaceAll("oś. ", "osiedle ")
            .replaceAll("oś ", "osiedle ")
            .replaceAll("gen. ", "generała ")
            .replaceAll("gen ", "generała ")
            .replaceAll("por. ", "porucznika ")
            .replaceAll("por ", "porucznika ")
            .replaceAll("kard. ", "kardynała ")
            .replaceAll("kard ", "kardynała ")
            .replaceAll("płk. ", "pułkownika ")
            .replaceAll("płk ", "pułkownika ")
            .replaceAll("ppłk. ", "podpułkownika ")
            .replaceAll("ppłk ", "podpułkownika ")
            .replaceAll("ks. ", "księdza ")
            .replaceAll("ks ", "księdza ")
            .replaceAll("bp. ", "biskupa ")
            .replaceAll("bp ", "biskupa ")
            .replaceAll("dr. ", "doktora ")
            .replaceAll("dr ", "doktora ")
            .replaceAll("prof. ", "profesora ")
            .replaceAll("prof ", "profesora ")
            .replaceAll("marsz. ", "marszałka ")
            .replaceAll("marsz ", "maszałka ")
            .replaceAll("rtm. ", "rotmistrza ")
            .replaceAll("rtm ", "rotmistrza ")
            .replaceAll("im ", "im. ")
            .replaceAll("pl. ", "plac ")
            .replaceAll("pl ", "plac ")
            .replaceAll("mjr. ", "majora ")
            .replaceAll("mjr ", "majora ")
            .replaceAll("imienia ", "im. ")
            .replaceAll("<", "")
            .replaceAll("  ", " ")
            .replaceAll("\"", "")
            .replaceAll("-", " ")
            .replaceAll("<", "")
            .replaceAll(">", "");
  }

  private void readDataHeader(File dataInputFile) throws IOException {
    Files.lines(dataInputFile.toPath()).findFirst().ifPresent(header -> {
      headers = header.split(";");
    });
  }
}
