package com.vikings.hackaton.demo.gui;

import com.google.common.base.Throwables;
import com.vikings.hackaton.demo.reader.PreparedDataReader;
import com.vikings.hackaton.demo.service.GeoPositionRetrievalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author lukaszgrabski
 */
public class MainWindow extends JFrame {

  private final static Logger LOGGER = LoggerFactory.getLogger(MainWindow.class);

  private JProgressBar progress;
  private GeoPositionRetrievalService geoPositionRetrievalService;
  private JLabel loadingLabel;
  private InputDataForm inputDataForm;

  public MainWindow() {
    super("Address Data");
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLayout(new BorderLayout());
    setResizable(false);

    initializeComponents();

    SwingWorker worker = new SwingWorker() {
      @Override
      protected Object doInBackground() throws Exception {
        try {
          loadData();
          displayFileInputForm();
        } catch (FileNotFoundException e) {
          LOGGER.error("An error occurred while loading data.", e);
          JOptionPane.showMessageDialog(MainWindow.this, Throwables.getRootCause(e).getMessage(), "Pro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
      }
    };
    worker.execute();
  }

  private void displayFileInputForm() {
    remove(progress);
    remove(loadingLabel);
    add(inputDataForm = new InputDataForm(geoPositionRetrievalService, file -> {
      displayDataGenerateForm(file);
    }), BorderLayout.CENTER);

    repaint();
    revalidate();
  }

  private void displayDataGenerateForm(File file) {
    remove(inputDataForm);
    try {
      add(new DataGenerateForm(file, geoPositionRetrievalService));
    } catch (IOException e) {
      LOGGER.error("!!!!!!", e);
      return;
    }
    repaint();
    revalidate();
  }

  private void initializeComponents() {
    add(loadingLabel = new JLabel("Loading data ..."), BorderLayout.PAGE_START);
    add(progress = new JProgressBar(), BorderLayout.PAGE_END);
    progress.setIndeterminate(true);
  }

  private void loadData() throws IOException {
    geoPositionRetrievalService = new GeoPositionRetrievalService(getPreparedDataReader());
    geoPositionRetrievalService.loadData();
  }

  public void display() {
    pack();
    setVisible(true);
    setSize(new Dimension(800, 600));
  }

  public PreparedDataReader getPreparedDataReader() throws FileNotFoundException {
    return new PreparedDataReader(new FileInputStream("malopolskie.csv"));
  }
}

