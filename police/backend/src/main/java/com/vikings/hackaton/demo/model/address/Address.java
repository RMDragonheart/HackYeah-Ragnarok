package com.vikings.hackaton.demo.model.address;

/**
 * @author lukaszgrabski
 */
public class Address {

  private String street;
  private String number;
  private AddressLocationType locationType;
  private int territoryCode;
  private AddressGeoData geoData;

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

  public AddressLocationType getLocationType() {
    return locationType;
  }

  public void setLocationType(AddressLocationType locationType) {
    this.locationType = locationType;
  }

  public int getTerritoryCode() {
    return territoryCode;
  }

  public void setTerritoryCode(int territoryCode) {
    this.territoryCode = territoryCode;
  }

  public AddressGeoData getGeoData() {
    return geoData;
  }

  public void setGeoData(AddressGeoData geoData) {
    this.geoData = geoData;
  }

  @Override
  public String toString() {
    return "Address{" +
            "street='" + street + '\'' +
            ", number='" + number + '\'' +
            ", locationType=" + locationType +
            ", territoryCode=" + territoryCode +
            ", geoData=" + geoData +
            '}';
  }
}
