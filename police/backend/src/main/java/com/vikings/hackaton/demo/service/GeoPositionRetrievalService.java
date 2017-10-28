package com.vikings.hackaton.demo.service;

import com.vikings.hackaton.demo.converter.XYData;
import com.vikings.hackaton.demo.model.StreetBuildingsLocations;
import com.vikings.hackaton.demo.model.address.AddressGeoData;
import com.vikings.hackaton.demo.reader.PreparedDataReader;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import static com.google.common.collect.Maps.newHashMap;

/**
 * @author lukaszgrabski
 */
public class GeoPositionRetrievalService {

  private PreparedDataReader dataReader;

  private Map<String, StreetBuildingsLocations> addressMap = newHashMap();

  public GeoPositionRetrievalService(PreparedDataReader osmDataReader) {
    this.dataReader = osmDataReader;
  }

  public void loadData() throws IOException {
    dataReader.readData(osmNode -> {
      String streetName = osmNode.getStreet();

      StreetBuildingsLocations buildingsLocations = addressMap.get(streetName);
      if (buildingsLocations == null) {
        buildingsLocations = new StreetBuildingsLocations();
        addressMap.put(streetName, buildingsLocations);
      }

      buildingsLocations.newBuilding(osmNode.getBuildingNumber(), osmNode.getX(), osmNode.getY());
    });
  }

  public Optional<XYData> getXYData(String street, String number) {
    StreetBuildingsLocations streetBuildingsLocations = addressMap.get(street);
    if (streetBuildingsLocations == null) {
      return Optional.empty();
    }
    return Optional.ofNullable(streetBuildingsLocations.getBuildingLocation(number));
  }
}
