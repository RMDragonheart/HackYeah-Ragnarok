package com.vikings.hackaton.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by patryk on 28.10.17.
 */
@Getter
@AllArgsConstructor
public class Injury {
    private int id;
    private String name;
    private BodyPart bodyPart;


}
