package com.vikings.hackaton.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Localisation {
    private int id;
    private String name;
    private double x;
    private double y;

}
