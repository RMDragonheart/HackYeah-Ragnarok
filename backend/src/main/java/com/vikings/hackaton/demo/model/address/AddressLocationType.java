package com.vikings.hackaton.demo.model.address;

/**
 * @author lukaszgrabski
 */
public enum AddressLocationType {

  STREET("ulica"),
  SETTLEMENT("osiedle"),
  SQUARE("plac");

  private final String typeName;

  AddressLocationType(String typeName) {
    this.typeName = typeName;
  }

  public static AddressLocationType byTypeName(String typeName) {
    for (AddressLocationType type : AddressLocationType.values()) {
      if (typeName.equals(type.typeName)) {
        return type;
      }
    }
    return null;
  }
}
