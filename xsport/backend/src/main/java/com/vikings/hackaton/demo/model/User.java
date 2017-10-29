package com.vikings.hackaton.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class User {
    private String name;
    private String password;
    private List<Integer> injuries;
    private double x;
    private double y;
}
