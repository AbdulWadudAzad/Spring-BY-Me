package com.coderbd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value = "/adm")
    public String adminView(){
        return "admin/admin";
    }

    @GetMapping(value = "/sa")
    public String superAdminView(){
        return "sadmin/sad";
    }

    @GetMapping(value = "/se")
    public String secureView(){
        return "secure/sec";
    }
    @GetMapping(value = "/u")
    public String userView(){
        return "user/u";
    }
}
