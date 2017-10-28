package com.vikings.hackaton.demo.controller;

import com.vikings.hackaton.demo.model.TestBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/timestamp")
    public TestBean timestamp() {
        return new TestBean(System.currentTimeMillis());
    }
}
