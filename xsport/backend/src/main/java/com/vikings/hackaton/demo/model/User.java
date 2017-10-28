package com.vikings.hackaton.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class User {
    private int id;
    private String name;
    private List<Injury> injuries;
    private int x;
    private int y;

//    public List<Sport> getSports() {
//        List<Sport> allSports = null; //TODO implement
//        return allSports.stream().filter(sport -> Collections.disjoint(injuries, sport.getExcludingInjuries())).collect(Collectors.toList());
//    }
}
