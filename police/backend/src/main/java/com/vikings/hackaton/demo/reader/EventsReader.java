package com.vikings.hackaton.demo.reader;

import com.google.common.base.Joiner;
import com.vikings.hackaton.demo.model.event.Event;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.newLinkedList;

/**
 * @author lukaszgrabski
 */
public class EventsReader {

  private final InputStream dataStream;
  private final String[] headers;
  private final List<String> headersInOrder;

  public EventsReader(InputStream dataStream, String[] headers, List<String> headersInOrder) {
    this.dataStream = dataStream;
    this.headers = headers;
    this.headersInOrder = headersInOrder;
  }

  public void parseEvents(Consumer<CSVRecord> recordConsumer) throws IOException {
    CSVParser csvParser = new CSVParser(new InputStreamReader(dataStream), CSVFormat.EXCEL.withHeader(headers).withDelimiter(';'));

    csvParser.forEach(record -> {
      if (record.getRecordNumber() == 1) {
        return;
      }
      recordConsumer.accept(record);
    });
  }

}
