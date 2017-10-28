package com.vikings.hackaton.demo.reader;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author lukaszgrabski
 */
public class OsmDataReaderTest {

  @Test
  public void testSomething() throws FileNotFoundException {
    // given
    OsmDataReader dataReader = new OsmDataReader(new FileInputStream("/opt/data/osm/malopolskie-latest.osm"));

    // when
    dataReader.readData(osmNode -> {
      System.out.println(osmNode);
    });
  }
}