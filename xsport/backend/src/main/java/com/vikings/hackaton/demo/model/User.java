package com.vikings.hackaton.demo.model;

import com.vikings.hackaton.demo.model.db.DatabaseConnector;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by patryk on 28.10.17.
 */
@Getter
public class User {
    private String name;

    private List<Injury> injuries;

    public List<Sport> getSports(){
        List<Sport> allSports = null; //TODO implement
        return allSports.stream().filter(sport -> Collections.disjoint(injuries, sport.getExcludingInjuries())).collect(Collectors.toList());
    }
}
