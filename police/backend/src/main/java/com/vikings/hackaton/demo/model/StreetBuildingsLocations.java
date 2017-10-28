package com.vikings.hackaton.demo.model;

import com.vikings.hackaton.demo.model.address.AddressGeoData;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

/**
 * @author lukaszgrabski
 */
public class StreetBuildingsLocations {

  private Map<String, AddressGeoData> buildings = newHashMap();

  public void newBuilding(String houseNumber, double latitude, double longitude) {
    buildings.put(houseNumber, new AddressGeoData(latitude, longitude));
  }

  public AddressGeoData getBuildingLocation(String number) {
    return buildings.get(number);
  }
}
