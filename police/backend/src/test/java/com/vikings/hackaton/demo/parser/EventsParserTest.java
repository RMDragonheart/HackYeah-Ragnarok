//package com.vikings.hackaton.demo.parser;
//
//import com.vikings.hackaton.demo.model.event.Event;
//import com.vikings.hackaton.demo.reader.EventsReader;
//import org.junit.Test;
//
//import java.io.IOException;
//import java.util.List;
//
///**
// * @author lukaszgrabski
// */
//public class EventsParserTest {
//
//  @Test
//  public void shouldParseEvents() throws IOException {
//    // given
//    EventsReader eventsParser = new EventsReader(getClass().getClassLoader().getResourceAsStream("events.csv"), headersInOrder);
//
//    // when
////    List<Event> events = eventsParser.parseEvents();
//
//    // then
//    events.forEach(event -> System.out.printf("event: %s \n", event));
//  }
//}