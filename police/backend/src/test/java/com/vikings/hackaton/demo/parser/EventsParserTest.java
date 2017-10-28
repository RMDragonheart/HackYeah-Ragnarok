package com.vikings.hackaton.demo.parser;

import com.vikings.hackaton.demo.model.event.Event;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author lukaszgrabski
 */
public class EventsParserTest {

  @Test
  public void shouldParseEvents() throws IOException {
    // given
    EventsParser eventsParser = new EventsParser(getClass().getClassLoader().getResourceAsStream("events.csv"));

    // when
    List<Event> events = eventsParser.parseEvents();

    // then
    events.forEach(event -> System.out.printf("event: %s \n", event));
  }
}