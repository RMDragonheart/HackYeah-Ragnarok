package com.vikings.hackaton.demo.reader.osm;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.function.Consumer;

import static com.google.common.collect.Maps.newHashMap;

/**
 * @author lukaszgrabski
 */
public class OsmDataHandler extends DefaultHandler {

  private final Consumer<OsmNode> osmNodeConsumer;
  private OsmNode osmNode;

  private Map<String, OsmNode> visitedNodes = newHashMap();

  public OsmDataHandler(Consumer<OsmNode> osmNodeConsumer) {
    this.osmNodeConsumer = osmNodeConsumer;
  }

  @Override
  public void startDocument() throws SAXException {
    super.startDocument();
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    if ("node".equals(localName)) {
      String latitude = attributes.getValue("lat");
      String longitude = attributes.getValue("lon");

      osmNode = new OsmNode(asDouble(latitude), asDouble(longitude));
      visitedNodes.put(attributes.getValue("id"), osmNode);
    }

    // node reference
    if ("nd".equals(localName)) {
      String nodeRefId = attributes.getValue("ref");
      OsmNode visitedNode = visitedNodes.get(nodeRefId);
      osmNode = new OsmNode(visitedNode.getLatitude(), visitedNode.getLongitude());
    }

    if ("tag".startsWith(localName) && osmNode != null) {
      String tagType = attributes.getValue("k");
      String tagValue = attributes.getValue("v");

      if ("addr:city".equals(tagType)) {
        osmNode.setCity(tagValue);
      } else if ("addr:street".equals(tagType)) {
        osmNode.setStreet(tagValue);
      } else if ("addr:housenumber".equals(tagType)) {
        osmNode.setHouseNumber(tagValue);
      }
    }
  }

  private double asDouble(String doubleStringValue) {
    return Double.parseDouble(doubleStringValue);
  }

  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
    if (("node".equals(localName) || "way".equals(localName)) && osmNode != null) {
      if (osmNode.hasAddressData()) {
        osmNodeConsumer.accept(osmNode);
      }
      osmNode = null;
    }
   }

  public final void read(InputStream xmlStream) throws ParsingException {
    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
    saxParserFactory.setNamespaceAware(true);

    try {
      SAXParser saxParser = saxParserFactory.newSAXParser();
      saxParser.parse(xmlStream, this);
    } catch (ParserConfigurationException | SAXException | IOException e) {
      throw new ParsingException(e);
    }
  }
}
