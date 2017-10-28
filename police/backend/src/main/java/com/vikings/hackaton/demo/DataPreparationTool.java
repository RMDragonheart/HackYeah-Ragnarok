package com.vikings.hackaton.demo;

import com.vikings.hackaton.demo.converter.GeoToXYConverter;
import com.vikings.hackaton.demo.converter.XYData;
import com.vikings.hackaton.demo.model.address.AddressGeoData;
import com.vikings.hackaton.demo.reader.OsmDataReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author lukaszgrabski
 */
public class DataPreparationTool {

  private static final String OSM_DATA_FILE = "/opt/data/osm/malopolskie-latest.osm";

  public static void main(String[] args) throws IOException {
    CSVPrinter csvPrinter = new CSVPrinter(new BufferedWriter(new FileWriter("/opt/data/osm/malopolskie.csv")), CSVFormat.newFormat(';').withRecordSeparator('\n'));
    GeoToXYConverter converter = new GeoToXYConverter();

    new OsmDataReader(new FileInputStream(OSM_DATA_FILE)).readData(osmNode -> {
      try {
        AddressGeoData addressGeoData = new AddressGeoData(osmNode.getLatitude(), osmNode.getLongitude());
        XYData xyData = converter.convertData(addressGeoData);
        csvPrinter.printRecord(osmNode.getCity(), osmNode.getStreet(), osmNode.getHouseNumber(), xyData.getX(), xyData.getY());
      } catch (IOException e) {
        e.printStackTrace();
      }
    });

    csvPrinter.flush();
    csvPrinter.close();
  }
}
