package com.vikings.hackaton.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Sport {
    private int id;
    private String name;
    private List<Integer> excludingInjuries;
    private List<Integer> localisations;


}
