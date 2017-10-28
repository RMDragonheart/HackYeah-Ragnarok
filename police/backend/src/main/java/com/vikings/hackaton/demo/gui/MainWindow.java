package com.vikings.hackaton.demo.gui;

import com.google.common.base.Throwables;
import com.vikings.hackaton.demo.reader.OsmDataReader;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author lukaszgrabski
 */
public class MainWindow extends JFrame {

  private final static Logger LOGGER = LoggerFactory.getLogger(MainWindow.class);

  private JProgressBar progress;
  private GeoPositionRetrievalService geoPositionRetrievalService;

  public MainWindow() {
    super("Address Data");
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLayout(new BorderLayout());
    initializeComponents();

    SwingWorker worker = new SwingWorker() {
      @Override
      protected Object doInBackground() throws Exception {
        try {
          loadData();
        } catch (FileNotFoundException e) {
          LOGGER.error("An error occurred while loading data.", e);
          JOptionPane.showMessageDialog(MainWindow.this, Throwables.getRootCause(e).getMessage(), "Error!!!!", JOptionPane.ERROR_MESSAGE);
        }
        return null;
      }
    };
    worker.execute();
  }

  private void initializeComponents() {
    add(new JLabel("Loading data ..."), BorderLayout.PAGE_START);

    add(progress = new JProgressBar(), BorderLayout.PAGE_END);
    progress.setIndeterminate(true);
  }

  private void loadData() throws FileNotFoundException {
    geoPositionRetrievalService = new GeoPositionRetrievalService(getOsmDataReader());
    geoPositionRetrievalService.loadData();
    progress.setIndeterminate(false);
  }

  public void display() {
    pack();
    setVisible(true);
    setSize(new Dimension(800, 600));
  }

  public OsmDataReader getOsmDataReader() throws FileNotFoundException {
    return new OsmDataReader(new FileInputStream("/opt/data/osm/malopolskie-latest.osm"));
  }
}

