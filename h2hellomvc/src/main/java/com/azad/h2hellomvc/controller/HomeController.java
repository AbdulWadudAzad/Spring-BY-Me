package com.azad.h2hellomvc.controller;

import com.azad.h2hellomvc.entity.Sudent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    static List<Sudent> list;

    @GetMapping(value = "/")
    @ResponseBody
    public String hello() {
        return "Hello ...............";
    }

    @GetMapping(value = "/test")
    @ResponseBody
    public String test() {
        return "Test ...............";
    }


    static {
        list = new ArrayList<>();
        list.add(new Sudent(1L, "asim", "012000000"));
        list.add(new Sudent(2L, "asin", "012000001"));
        list.add(new Sudent(3L, "asio", "012000002"));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Sudent> getList() {
        return this.list;
    }

    @GetMapping(value = "/sudent")
    @ResponseBody
    public Sudent getSudent(){
        return new Sudent(1L, "asim", "012000000");
    }

    @GetMapping(value = "/sudent/{id}")
    @ResponseBody
    public Sudent getSudentById(@PathVariable("id") Long  sudentid){
        Sudent sudent =null;
        for (Sudent s: this.list){
            if(sudentid==s.getId()){
                sudent=new Sudent();
              sudent=new Sudent(s.getId(),s.getName(),s.getMobile());
              break;
            }
        }
        return sudent;
    }

}
