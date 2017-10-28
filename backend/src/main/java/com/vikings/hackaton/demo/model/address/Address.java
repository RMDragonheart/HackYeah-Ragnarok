package com.vikings.hackaton.demo.model.address;

/**
 * @author lukaszgrabski
 */
public class Address {

  private String street;
  private String number;
  private AddressLocationType locationType;
  private int territoryCode;

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
}
