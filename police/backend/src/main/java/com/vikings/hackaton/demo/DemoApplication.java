package com.vikings.hackaton.demo;


import com.vikings.hackaton.demo.gui.MainWindow;

public class DemoApplication {

  public static void main(String[] args) {
    new DemoApplication().doShit();
  }

  private void doShit() {
    new MainWindow().display();
  }
}
