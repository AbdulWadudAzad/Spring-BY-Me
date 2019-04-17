package com.azad.templatequickjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value = "/index")
    public String displayDashboard(){
        return "index";
    }
//    @GetMapping(value = "/about")
//    public String displayAbout(){
//        return "about";
//    }
}
