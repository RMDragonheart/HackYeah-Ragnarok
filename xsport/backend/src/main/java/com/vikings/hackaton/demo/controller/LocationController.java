package com.vikings.hackaton.demo.controller;

import com.vikings.hackaton.demo.model.Injury;
import com.vikings.hackaton.demo.model.Localisation;
import com.vikings.hackaton.demo.model.db.DatabaseConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/{id}")
    public List<Localisation> sportLocations(@PathVariable Integer id) {
        List<Integer> localisationsIds = databaseConnector.getSports().stream().filter(sport1 -> sport1.getId() == id).findFirst().get().getLocalisations();
        return databaseConnector.getLocalisations().stream().filter(localisation -> localisationsIds.contains(localisation.getId())).collect(Collectors.toList());
    }

    @PutMapping("/add")
    public void addLocalisation(@RequestBody Localisation localisation) {
        databaseConnector.addLocalisation(localisation);
    }

}
