package com.vikings.hackaton.demo.reader;

import com.vikings.hackaton.demo.reader.osm.OsmDataHandler;
import com.vikings.hackaton.demo.reader.osm.OsmNode;

import java.io.InputStream;
import java.util.function.Consumer;

/**
 * @author lukaszgrabski
 */
public class OsmDataReader {

  private final InputStream osmSataStream;

  public OsmDataReader(InputStream osmSataStream) {
    this.osmSataStream = osmSataStream;
  }

  public void readData(Consumer<OsmNode> osmNodeConsumer) {
    new OsmDataHandler(osmNodeConsumer).read(osmSataStream);
  }
}
