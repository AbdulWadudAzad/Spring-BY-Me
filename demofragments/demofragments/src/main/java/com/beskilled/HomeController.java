package com.beskilled;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value = "/i")
    public String index(){
        return "index";
    }
    @GetMapping(value = "/")
    public String indext(){
        return "layout";
    }
    @GetMapping(value = "/t")
    public String test(){
        return "test";
    }
    @GetMapping(value = "/f")
    public String from(){
        return "from";
    }
}
