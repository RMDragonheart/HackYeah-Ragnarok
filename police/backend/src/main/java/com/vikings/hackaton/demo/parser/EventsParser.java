package com.vikings.hackaton.demo.parser;

import com.vikings.hackaton.demo.model.event.Event;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author lukaszgrabski
 */
public class EventsParser {

  private final InputStream dataStream;

  public EventsParser(InputStream dataStream) {
    this.dataStream = dataStream;
  }

  public List<Event> parseEvents() throws IOException {
    List<Event> eventList = newArrayList();
    CSVParser csvParser = new CSVParser(new InputStreamReader(dataStream), CSVFormat.newFormat(';'));
    csvParser.forEach(record -> {
      String street = record.get(1);
      String number = record.get(2);

      eventList.add(new Event(street, number));
    });
    return eventList;
  }
}
