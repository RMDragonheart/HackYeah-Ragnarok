package com.vikings.hackaton.demo.controller;

import com.vikings.hackaton.demo.model.User;
import com.vikings.hackaton.demo.model.db.DatabaseConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private DatabaseConnector databaseConnector;

    //TODO do everything here properly with SSL and so on
    @PutMapping("/register")
    public void register(@RequestBody User user) {
        databaseConnector.addUser(user);
    }

    @GetMapping("/login")
    public User login(@RequestBody User user) throws Exception {
        return databaseConnector.getUsers().stream().filter(u -> u.getName().equals(user.getName()) && u.getPassword().equals(user.getPassword())).findFirst().orElseThrow(() -> new Exception("Incorrect credentials, please try again"));
    }

}
