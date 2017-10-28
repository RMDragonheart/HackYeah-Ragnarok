package com.vikings.hackaton.demo.reader.osm;

/**
 * @author lukaszgrabski
 */
public class ParsingException extends RuntimeException {
  public ParsingException(Exception e) {
    super(e);
  }
}
