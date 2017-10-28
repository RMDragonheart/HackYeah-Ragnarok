package com.vikings.hackaton.demo.model;

import com.vikings.hackaton.demo.converter.XYData;
import com.vikings.hackaton.demo.model.address.AddressGeoData;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

/**
 * @author lukaszgrabski
 */
public class StreetBuildingsLocations {

  private Map<String, XYData> buildings = newHashMap();

  public void newBuilding(String houseNumber, String x, String y) {
    buildings.put(houseNumber, new XYData(x, y));
  }

  public XYData getBuildingLocation(String number) {
    return buildings.get(number);
  }
}
