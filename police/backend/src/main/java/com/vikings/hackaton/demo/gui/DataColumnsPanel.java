package com.vikings.hackaton.demo.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;

import static com.google.common.collect.Lists.newLinkedList;

/**
 * @author lukaszgrabski
 */
public class DataColumnsPanel extends JPanel {

  private final Consumer<List<String>> headersConsumer;
  private java.util.List<String> headers = newLinkedList();

  public DataColumnsPanel(String[] headers, Consumer<List<String>> headersConsumer) {
    this.headersConsumer = headersConsumer;
    setLayout(new FlowLayout());
    addButtons(headers);
  }

  private void addButtons(String[] headers) {
    Arrays.stream(headers).forEach(header -> {
      JToggleButton toggleHeaderButton = new JToggleButton(header);
      toggleHeaderButton.addActionListener(headerToggleListener(toggleHeaderButton, header));
      add(toggleHeaderButton);
    });
  }

  private ActionListener headerToggleListener(JToggleButton thisButton, String headerName) {
    return e -> {
      if (thisButton.isSelected()) {
        headers.add(headerName);
      } else {
        headers.remove(headerName);
      }
      headersConsumer.accept(headers);
    };
  }
}
