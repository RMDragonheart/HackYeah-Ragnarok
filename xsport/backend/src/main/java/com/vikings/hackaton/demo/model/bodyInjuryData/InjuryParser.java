package com.vikings.hackaton.demo.model.bodyInjuryData;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

class InjuryParser {

	static void runInjuryParser() throws IOException {
		PrintWriter save = new PrintWriter("xsport/backend/src/main/resources/Injury/EntireInjury.txt");
		File input = new File("xsport/backend/src/main/resources/Injury/WebData.html");
		Document doc = Jsoup.parse(input, "UTF-8", "http://lista.icd10.pl/");
//		Document doc = Jsoup.connect("http://lista.icd10.pl/").maxBodySize(0).timeout(Integer.MAX_VALUE).get();
//		Elements tag = doc.select("div[id^=hidden]");
//		Elements tag = doc.select("div[id=hidden16]");
		Elements tag = doc.select("div[id=hidden1]");
		for (Element element : tag) {
			String text = element.text();
			if (!text.isEmpty()) {
				text = text.replaceAll("\u00A0", " ");
				String[] separatedData = text.split("\\s{2,}");
				for (String s : separatedData) {
					StringBuilder idBuilder = new StringBuilder();

					for (int i = 0; i < s.length(); i++) {
						if (s.charAt(i) != ' ') {
							idBuilder.append(s.charAt(i));
						} else {
							break;
						}
					}

					String id = idBuilder.toString();
					if (StringUtils.countOccurrencesOf(s, id) > 1) {
						// We know that ID is duplicated in that row. We have to remove it
						if (id.contains("-")) {
							String[] data = parseDashString(id, s);
							save.println(data[0]);
							save.println(data[1]);
							continue;
						} else {
							s = parseNormalString(id, s);
						}
					}

					save.println(s);
				}
			}
		}

		save.close();
		System.out.println("Crawling and data parsing - SUCCEEDED!");
	}

	private static String[] parseDashString(String id, String stringToParse) {
		String[] output = new String[2];
		int index = stringToParse.indexOf(id, id.length());
		output[0] = stringToParse.substring(0, index);
		String anotherId = id.substring(0, 3);
		int startIndex = stringToParse.indexOf(anotherId, index + id.length());
		int endIndex = stringToParse.indexOf(anotherId, startIndex + anotherId.length());
		output[1] = stringToParse.substring(startIndex, endIndex - 1);

		return output;
	}

	private static String parseNormalString(String id, String stringToParse) {
		int index = stringToParse.indexOf(id, id.length());
		return stringToParse.substring(0, index - 1);
	}
}
