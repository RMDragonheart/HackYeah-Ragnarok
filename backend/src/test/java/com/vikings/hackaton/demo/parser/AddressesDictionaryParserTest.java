package com.vikings.hackaton.demo.parser;


import org.junit.Test;

import java.io.IOException;

/**
 * @author lukaszgrabski
 */
public class AddressesDictionaryParserTest {

  @Test
  public void shouldLoadAllAdressesFromCSVFile() throws IOException {
    // given
    AddressesDictionaryReader dictionaryReader = new AddressesDictionaryReader(getClass().getClassLoader().getResourceAsStream("addresses.csv"));

    // when
    dictionaryReader.parseAddresses().forEach(address -> System.out.printf("Address: %s \n", address));
  }
}