package com.vikings.hackaton.demo.controller;

import com.vikings.hackaton.demo.model.Injury;
import com.vikings.hackaton.demo.model.Localisation;
import com.vikings.hackaton.demo.model.db.DatabaseConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private DatabaseConnector databaseConnector;

    @GetMapping
    public List<Localisation> locations() {
        return databaseConnector.getLocalisations();
    }

    @PostMapping
    public List<Localisation> availableLocations(@RequestBody List<Injury> injuries) {
        List<Integer> injuriesIds = injuries.stream().map(Injury::getId).collect(Collectors.toList());
        Map<Integer, Localisation> localisations = databaseConnector.getLocalisations().stream().collect(Collectors.toMap(Localisation::getId, Function.identity()));
        return databaseConnector.getSports().stream().filter(sport ->
                Collections.disjoint(sport.getExcludingInjuries(), injuriesIds))
                .flatMap(sport -> sport.getLocalisations().stream())
                .map(localisations::get).collect(Collectors.toList());
    }

}
