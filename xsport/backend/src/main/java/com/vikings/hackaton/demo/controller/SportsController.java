package com.vikings.hackaton.demo.controller;

import com.vikings.hackaton.demo.model.Injury;
import com.vikings.hackaton.demo.model.Sport;
import com.vikings.hackaton.demo.model.db.DatabaseConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by patryk on 28.10.17.
 */
@RestController
@RequestMapping("/")
public class SportsController {

    @Autowired
    DatabaseConnector databaseConnector;

    @GetMapping("/sports")
    public List<Sport> sports() {
        return databaseConnector.getSports();
    }

    @GetMapping("/sports/available")
    public List<Sport> availableSports(@RequestBody List<Injury> injuries) {
        List<Integer> injuriesIds = injuries.stream().map(Injury::getId).collect(Collectors.toList());
        return databaseConnector.getSports().stream().filter(sport ->
                Collections.disjoint(sport.getExcludingInjuries(), injuriesIds)).collect(Collectors.toList());
    }
}
