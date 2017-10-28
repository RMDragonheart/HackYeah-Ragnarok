package com.vikings.hackaton.demo.service;

import com.vikings.hackaton.demo.model.StreetBuildingsLocations;
import com.vikings.hackaton.demo.model.address.AddressGeoData;
import com.vikings.hackaton.demo.reader.OsmDataReader;

import java.util.Map;
import java.util.Optional;

import static com.google.common.collect.Maps.newHashMap;

/**
 * @author lukaszgrabski
 */
public class GeoPositionRetrievalService {

  private OsmDataReader osmDataReader;

  private Map<String, StreetBuildingsLocations> addressMap = newHashMap();

  public GeoPositionRetrievalService(OsmDataReader osmDataReader) {
    this.osmDataReader = osmDataReader;
  }

  public void loadData() {
    osmDataReader.readData(osmNode -> {
      String streetName = osmNode.getStreet();

      StreetBuildingsLocations buildingsLocations = addressMap.get(streetName);
      if (buildingsLocations == null) {
        buildingsLocations = new StreetBuildingsLocations();
        addressMap.put(streetName, buildingsLocations);
      }

      buildingsLocations.newBuilding(osmNode.getHouseNumber(), osmNode.getLatitude(), osmNode.getLongitude());
    });
  }

  public Optional<AddressGeoData> getGeoPosition(String street, String number) {
    StreetBuildingsLocations streetBuildingsLocations = addressMap.get(street);
    if (streetBuildingsLocations == null) {
      return Optional.empty();
    }
    return Optional.ofNullable(streetBuildingsLocations.getBuildingLocation(number));
  }
}
