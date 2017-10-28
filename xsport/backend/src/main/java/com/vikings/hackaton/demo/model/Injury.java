package com.vikings.hackaton.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Injury {
    private int id;
    private String name;
    private BodyPart bodyPart;


}
