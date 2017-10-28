//package com.vikings.hackaton.demo.service;
//
//import com.vikings.hackaton.demo.model.address.AddressGeoData;
//import com.vikings.hackaton.demo.reader.OsmDataReader;
//import org.junit.Test;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.util.Optional;
//
//import static org.fest.assertions.Assertions.assertThat;
//
///**
// * @author lukaszgrabski
// */
//public class GeoPositionRetrievalServiceTest {
//
//  @Test
//  public void testBleBle() throws FileNotFoundException {
//    // given
////    GeoPositionRetrievalService geoPositionRetrievalService = new GeoPositionRetrievalService(new OsmDataReader(new FileInputStream("/opt/data/osm/malopolskie-latest.osm")));
////    geoPositionRetrievalService.loadData();
//
////    // when
////    long start = System.currentTimeMillis();
////    Optional<AddressGeoData> addressGeoData = geoPositionRetrievalService.getXYData("Pruska", "1");
////    System.out.printf("Took for one address: %s \n", System.currentTimeMillis() - start);
//
//
//    // then
//    assertThat(addressGeoData.isPresent()).isTrue();
//
//    System.out.println(addressGeoData);
//  }
//}