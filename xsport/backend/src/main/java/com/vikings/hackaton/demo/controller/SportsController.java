package com.vikings.hackaton.demo.controller;

import com.vikings.hackaton.demo.model.Injury;
import com.vikings.hackaton.demo.model.Sport;
import com.vikings.hackaton.demo.model.db.DatabaseConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sports")
public class SportsController {

    @Autowired
    private DatabaseConnector databaseConnector;

    @GetMapping
    public List<Sport> sports() {
        return databaseConnector.getSports();
    }

    @PostMapping
    public List<Sport> availableSports(@RequestBody List<Injury> injuries) {
        List<Integer> injuriesIds = injuries.stream().map(Injury::getId).collect(Collectors.toList());
        return databaseConnector.getSports().stream().filter(sport ->
                Collections.disjoint(sport.getExcludingInjuries(), injuriesIds)).collect(Collectors.toList());
    }
}
