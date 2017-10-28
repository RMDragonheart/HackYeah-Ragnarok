package com.vikings.hackaton.demo.gui;

import com.vikings.hackaton.demo.service.GeoPositionRetrievalService;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;

/**
 * @author lukaszgrabski
 */
public class DataForm extends JPanel {

  private final GeoPositionRetrievalService geoPositionRetrievalService;
  private JButton button;
  private JTextField street;
  private JTextField houseNumber;

  public DataForm(GeoPositionRetrievalService geoPositionRetrievalService) {
    this.geoPositionRetrievalService = geoPositionRetrievalService;

    setLayout(new GridLayout(3, 2));
    initializeComponents();
  }

  private void initializeComponents() {
    add(new JLabel("Street"));
    add(street = new JTextField());
    add(new JLabel("House number"));
    add(houseNumber = new JTextField());
    add(button = new JButton("Find GEO data"));

    button.addActionListener(e -> {
      geoPositionRetrievalService.getGeoPosition(street.getText(), houseNumber.getText()).ifPresent(addressGeoData -> {
        JOptionPane.showMessageDialog(DataForm.this, addressGeoData.toString(), "Results", JOptionPane.INFORMATION_MESSAGE);
      });
    });
  }
}
