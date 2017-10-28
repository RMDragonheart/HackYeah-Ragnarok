package com.vikings.hackaton.demo.model.bodyInjuryData;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class InjuryParser {

	public static void main(String[] args) throws IOException {
		Map<String, String[]> entireInjury = new HashMap<>();
		File input = new File("xsport/backend/src/main/resources/Injury/WebData.html");
		Document doc = Jsoup.parse(input, "UTF-8", "http://lista.icd10.pl/");
		//Document doc = Jsoup.connect("http://lista.icd10.pl/").maxBodySize(0).timeout(Integer.MAX_VALUE).get();
//		Elements tag = doc.select("div[id^=hidden]");
		PrintWriter save = new PrintWriter("xsport/backend/src/main/resources/Injury/EntireInjury.txt");
		Elements tag = doc.select("div[id=hidden16]");
		for (Element element : tag) {
			String text = element.text();
			if (!text.isEmpty()) {
				StringBuffer fuckingMarkDeleter = new StringBuffer();
				for (int i = 0; i < text.length(); i++) {
					if (Byte.valueOf("" + text.charAt(i)) != -96) {
						System.out.println("YEEEE!");
					}
				}
				String[] separatedData = text.split("\\s{2,}");
				for (String s : separatedData) {
					save.println(s);
					System.out.println("I get: " + s);
				}
			}
		}

		save.close();
		System.out.println("Connection successful!");
	}
}
