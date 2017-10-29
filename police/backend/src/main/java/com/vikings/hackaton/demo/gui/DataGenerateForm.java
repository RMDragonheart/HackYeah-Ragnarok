package com.vikings.hackaton.demo.gui;

import com.vikings.hackaton.demo.reader.EventsReader;
import com.vikings.hackaton.demo.service.GeoPositionRetrievalService;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author lukaszgrabski
 */
public class DataGenerateForm extends JPanel {

  private final JButton actionButton;
  private String[] headers;
  private java.util.List<String> headersInOrder;

  public DataGenerateForm(File dataInputFile, GeoPositionRetrievalService geoPositionRetrievalService) throws IOException {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    readDataHeader(dataInputFile);

    add(new DataColumnsPanel(headers, headers -> {
      headersInOrder = headers;
    }));

    add(actionButton = new JButton("DO IT!"));
    actionButton.addActionListener(e -> {
      try {
        new EventsReader(new FileInputStream(dataInputFile), headers, headersInOrder).parseEvents();
      } catch (IOException e1) {
        e1.printStackTrace();
      }
    });
  }

  private void readDataHeader(File dataInputFile) throws IOException {
    Files.lines(dataInputFile.toPath()).findFirst().ifPresent(header -> {
      headers = header.split(";");
    });
  }
}
