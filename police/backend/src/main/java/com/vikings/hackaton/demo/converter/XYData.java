package com.vikings.hackaton.demo.converter;

/**
 * @author lukaszgrabski
 */
public class XYData {

  private String x;
  private String y;

  public XYData(String x, String y) {
    this.x = x;
    this.y = y;
  }

  public String getX() {
    return x;
  }

  public void setX(String x) {
    this.x = x;
  }

  public String getY() {
    return y;
  }

  public void setY(String y) {
    this.y = y;
  }

  @Override
  public String toString() {
    return "XYData{" +
            "x='" + x + '\'' +
            ", y='" + y + '\'' +
            '}';
  }
}
