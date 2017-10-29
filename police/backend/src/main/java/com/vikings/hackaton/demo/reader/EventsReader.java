package com.vikings.hackaton.demo.reader;

import com.google.common.base.Joiner;
import com.vikings.hackaton.demo.model.event.Event;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.newLinkedList;

/**
 * @author lukaszgrabski
 */
public class EventsReader {

  private final InputStream dataStream;
  private final String[] headers;
  private final List<String> headersInOrder;

  public EventsReader(InputStream dataStream, String[] headers, List<String> headersInOrder) {
    this.dataStream = dataStream;
    this.headers = headers;
    this.headersInOrder = headersInOrder;
  }

  public void parseEvents() throws IOException {
    CSVParser csvParser = new CSVParser(new InputStreamReader(dataStream), CSVFormat.EXCEL.withHeader(headers).withDelimiter(';'));

    csvParser.forEach(record -> {
      if (record.getRecordNumber() == 1) {
        return;
      }
      processRecord(record);
    });
  }

  private void processRecord(CSVRecord record) {
    List<String> steetNameElements = newLinkedList();

    headersInOrder.forEach(headerInOrder -> {
      String partialValue = record.get(headerInOrder);
      steetNameElements.add(partialValue);
    });

    String address = normalize(Joiner.on(" ").join(steetNameElements));
    String[] addressParts = address.split(" ");
    String streetPart = Joiner.on(" ").join(Arrays.copyOf(addressParts, addressParts.length - 1));
    String houseNumberPart = addressParts[addressParts.length - 1];

  }

  private String normalize(String street) {
    return street.trim().toLowerCase()
            .replaceAll("ul. ", "")
            .replaceAll("ul ", "")
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
            .replaceAll("imienia ", "im. ")
            .replaceAll("<", "")
            .replaceAll("  ", " ")
            .replaceAll("\"", "");
  }
}
