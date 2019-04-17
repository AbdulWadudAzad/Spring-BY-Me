package com.coderbd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value = "/")
    public String displayDashboard(){
        return "dashboard";
    }
    @GetMapping(value = "/about")
    public String displayAbout(){
        return "about";
    }
}
