package com.vikings.hackaton.demo.model.address;

/**
 * @author lukaszgrabski
 */
public class AddressGeoData {

  public AddressGeoData(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  private double latitude;
  private double longitude;

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  @Override
  public String toString() {
    return "AddressGeoData{" +
            "latitude=" + latitude +
            ", longitude=" + longitude +
            '}';
  }
}
