package com.azad.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping(value = "/")
    public String home() {
        return "Hello Mvc";

    }

    @GetMapping(value = "/test")
    public String test() {
        return "Hello Test";

    }
}
