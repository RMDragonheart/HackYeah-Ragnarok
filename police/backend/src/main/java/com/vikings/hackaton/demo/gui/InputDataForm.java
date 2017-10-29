package com.vikings.hackaton.demo.gui;

import com.vikings.hackaton.demo.service.GeoPositionRetrievalService;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.function.Consumer;

/**
 * @author lukaszgrabski
 */
public class InputDataForm extends JPanel {

  private final GeoPositionRetrievalService geoPositionRetrievalService;
  private final Consumer<File> fileConsumer;
  private JButton button;
  private JTextField street;
  private JTextField houseNumber;
  private JButton openFileButton;
  private JFileChooser fileChooser;

  public InputDataForm(GeoPositionRetrievalService geoPositionRetrievalService, Consumer<File> fileConsumer) {
    this.geoPositionRetrievalService = geoPositionRetrievalService;
    this.fileConsumer = fileConsumer;

    setLayout(new BorderLayout(5, 5));
    initializeComponents();
  }

  private void initializeComponents() {
    fileChooser = new JFileChooser();

    add(new JLabel("Pick up input data file (CSV):"), BorderLayout.PAGE_START);
    add(openFileButton = new JButton("Open file"));
    openFileButton.addActionListener(e -> {
      int returnVal = fileChooser.showOpenDialog(InputDataForm.this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        fileConsumer.accept(file);
      }
    });
  }
}
