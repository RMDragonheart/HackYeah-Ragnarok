package com.vikings.hackaton.demo.service;

import com.vikings.hackaton.demo.converter.XYData;
import com.vikings.hackaton.demo.model.StreetBuildingsLocations;
import com.vikings.hackaton.demo.model.address.AddressGeoData;
import com.vikings.hackaton.demo.reader.PreparedDataReader;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

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
      String streetName = osmNode.getStreet().toLowerCase();

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

  public Optional<XYData> getXYDataPartial(String streetPart, String number) {
    for (Map.Entry<String, StreetBuildingsLocations> stringStreetBuildingsLocationsEntry : addressMap.entrySet()) {
      String streetName = stringStreetBuildingsLocationsEntry.getKey();
      if (streetName.contains(streetPart)) {
        XYData xyData = stringStreetBuildingsLocationsEntry.getValue().getBuildingLocation(number);
        System.out.printf("%s, %s = %s \n", streetPart, number, xyData);
        return Optional.ofNullable(xyData);
      }
    }
    return Optional.empty();
  }

  public void forEachStreet(Consumer<String> streetConsumer) {
    addressMap.keySet().forEach(streetConsumer);
  }
}
