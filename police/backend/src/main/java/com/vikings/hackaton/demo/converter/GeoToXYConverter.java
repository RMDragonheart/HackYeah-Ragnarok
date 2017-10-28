package com.vikings.hackaton.demo.converter;

import com.vikings.hackaton.demo.model.address.AddressGeoData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author lukaszgrabski
 */
public class GeoToXYConverter {

  public XYData convertData(AddressGeoData geoData) {
    double latitude = geoData.getLatitude();
    double longitude = geoData.getLongitude();

    try {
      String xyDataString = executeProcess(latitude, longitude);
      String[] dataParts = xyDataString.split(";");

      return new XYData(dataParts[0], dataParts[1]);
    } catch (Throwable e) {
      e.printStackTrace();
    }
    return null;
  }

  private String executeProcess(double latitude, double longitude) throws IOException, InterruptedException {
    ProcessBuilder processBuilder = new ProcessBuilder("gpstoxy", ""+latitude, ""+longitude);
    BufferedReader output = getOutput(processBuilder.start());
    return output.readLine();
  }

  private static BufferedReader getOutput(Process p) {
    return new BufferedReader(new InputStreamReader(p.getInputStream()));
  }
}
