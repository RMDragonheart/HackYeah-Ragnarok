package com.vikings.hackaton.demo.model.bodyInjuryData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InjuryMapper {
	private static Map<String, Map<String, Map<String, String>>> entireInjury = new HashMap<>();

	public static Map<String, Map<String, Map<String, String>>> getParsedInjuryMap() throws IOException {
		InjuryParser.runInjuryParser();

		BufferedReader reader = new BufferedReader(new FileReader(
						"xsport/backend/src/main/resources/Injury/EntireInjury.txt"));

		String currentLine;
		List<String> tmp = new ArrayList<>();

		do {
			currentLine = reader.readLine();
			tmp.add(currentLine);
		} while (currentLine != null);

		Map<String, Map<String, String>> injuryCategory = new HashMap<>();
		Map<String, String> singleInjury = new HashMap<>();

		for (int i = tmp.size() - 1; i >= 0; i--) {
			if (i == tmp.size()-1) {
				continue;
			}

			StringBuilder singleInjuryIdBuilder = new StringBuilder();
			currentLine = tmp.get(i);

			for (int j = 0; j < currentLine.length(); j++) {
				if (currentLine.charAt(j) != ' ') {
					singleInjuryIdBuilder.append(currentLine.charAt(j));
				} else {
					break;
				}
			}

			String singleInjuryId = singleInjuryIdBuilder.toString();

			if (singleInjuryId.contains(".")) {
				singleInjury.put(singleInjuryId, currentLine);
			} else if (singleInjuryId.contains("-")) {
				entireInjury.put(singleInjuryId, injuryCategory);
				injuryCategory = new HashMap<>();
			} else {
				injuryCategory.put(singleInjuryId, singleInjury);
				singleInjury = new HashMap<>();
			}
		}

		System.out.println("Mapping SUCCEEDED!");
		
		return entireInjury;
	}
}
