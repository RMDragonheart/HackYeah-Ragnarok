package com.vikings.hackaton.demo.model.bodyInjuryData;

import java.io.IOException;
import java.util.Map;

public class InjuryCategorizer {
	private Map<String, Map<String, Map<String, String>>> entireInjury;
	private Map<String, String> injuriesCategories;

	public InjuryCategorizer() throws IOException {
		this.entireInjury = InjuryMapper.getParsedInjuryMap();
	}

	public InjuryCategorizer(Map<String, Map<String, Map<String, String>>> entireInjury) {
		this.entireInjury = entireInjury;
	}

	// TODO In the full APP version we can use entire Injury Data.
}
