package com.vikings.hackaton.demo.model.event;

/**
 * @author lukaszgrabski
 */
public class Event {

  private String street;
  private String number;

  public Event(String street, String number) {
    this.street = street;
    this.number = number;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  @Override
  public String toString() {
    return "Event{" +
            "street='" + street + '\'' +
            ", number='" + number + '\'' +
            '}';
  }
}
