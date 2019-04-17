package com.azad.templatequickjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
//    @GetMapping(value = "/")
//    public String displayDashboard(){
//        return "index";
//    }

    @GetMapping(value = "/cv")
    public String displayCv(){
        return "cv";
    }

    @GetMapping(value = "/jobadd")
    public String displayjobpost(){
        return "jobpost/add";
    }

//    @GetMapping(value = "/publicview")
//    public String publicview(){
//        return "publicview";
//    }

//    @GetMapping(value = "/s")
//    public String displayProfile(){
//        return "s";
//    }

}
