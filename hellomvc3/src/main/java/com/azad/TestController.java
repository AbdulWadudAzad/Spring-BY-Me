package com.azad;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = "/tt")
    public static void main(String[] args) {
        System.out.println("hi dear BD");
    }


    @GetMapping(value = "/tt")
    public String test(@RequestParam(value = "ms", required = true, defaultValue = "Hello World") String msg) {
        return msg;
    }
}
