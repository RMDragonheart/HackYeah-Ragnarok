package com.vikings.hackaton.demo.reader;

import com.vikings.hackaton.demo.model.address.Address;
import com.vikings.hackaton.demo.model.address.AddressGeoData;
import com.vikings.hackaton.demo.model.address.AddressLocationType;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Double.parseDouble;

/**
 * @author lukaszgrabski
 */
public class AddressesDictionaryReader {

  private final InputStream dataStream;

  public AddressesDictionaryReader(InputStream dataStream) {
    this.dataStream = dataStream;
  }

  public List<Address> parseAddresses() throws IOException {
    List<Address> addressList = newArrayList();
    CSVParser csvParser = new CSVParser(new InputStreamReader(dataStream), CSVFormat.newFormat(';'));
    csvParser.forEach(record -> {
      if (record.getRecordNumber() <= 1) {
        return;
      }
      Address addressEntry = new Address();
      addressEntry.setStreet(record.get(0));
      addressEntry.setNumber(record.get(1));
      addressEntry.setLocationType(AddressLocationType.byTypeName(record.get(6)));

      String territoryCode = record.get(7);
      if (!isMissingTerritoryCode(territoryCode)) {
        addressEntry.setTerritoryCode(Integer.parseInt(record.get(7)));
      }
      addressEntry.setGeoData(new AddressGeoData(asDouble(record.get(10)), asDouble(record.get(11))));
      addressList.add(addressEntry);
    });
    return addressList;
  }

  private double asDouble(String doubleStringValue) {
    return parseDouble(doubleStringValue.replaceAll(",", "."));
  }

  private boolean isMissingTerritoryCode(String territoryCode) {
    return "brak".equals(territoryCode);
  }

}
