package com.vikings.hackaton.demo.controller;

import com.vikings.hackaton.demo.model.BodyPart;
import com.vikings.hackaton.demo.model.Injury;
import com.vikings.hackaton.demo.model.db.DatabaseConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/injuries")
public class InjuriesController {

    @Autowired
    private DatabaseConnector databaseConnector;

    @GetMapping("/")
    public List<Injury> injuries() {
        return databaseConnector.getInjuries();
    }

    @GetMapping("/{bodyPart}")
    public List<Injury> injuriesOf(@PathVariable String bodyPart) {
        return databaseConnector.getInjuries().stream()
                .filter(injury -> injury.getBodyPart() == BodyPart.valueOf(bodyPart.toUpperCase()))
                .collect(Collectors.toList());
    }
}
