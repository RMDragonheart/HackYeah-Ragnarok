package com.vikings.hackaton.demo.reader;

import com.vikings.hackaton.demo.model.PreparedAddress;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;

/**
 * @author lukaszgrabski
 */
public class PreparedDataReader {

  private final InputStream dataStream;

  public PreparedDataReader(InputStream dataStream) {
    this.dataStream = dataStream;
  }

  public void readData(Consumer<PreparedAddress> osmNodeConsumer) throws IOException {
    CSVParser csvParser = new CSVParser(new InputStreamReader(dataStream), CSVFormat.newFormat(';'));
    csvParser.forEach(record -> {
      PreparedAddress preparedAddress = new PreparedAddress();
      preparedAddress.setCity(record.get(0));
      preparedAddress.setStreet(record.get(1));
      preparedAddress.setBuildingNumber(record.get(2));
      preparedAddress.setX(record.get(3));
      preparedAddress.setY(record.get(4));

      osmNodeConsumer.accept(preparedAddress);
    });
  }
}
