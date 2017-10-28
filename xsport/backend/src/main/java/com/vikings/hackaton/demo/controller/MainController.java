package com.vikings.hackaton.demo.controller;

import com.vikings.hackaton.demo.model.Injury;
import com.vikings.hackaton.demo.model.Sport;
import com.vikings.hackaton.demo.model.db.DatabaseConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by patryk on 28.10.17.
 */
@RestController
@RequestMapping("/")
public class MainController {

    @Autowired
    DatabaseConnector databaseConnector;

    @GetMapping("/sports")
    public List<Sport> sports() {
        return databaseConnector.getSports();
    }
}
