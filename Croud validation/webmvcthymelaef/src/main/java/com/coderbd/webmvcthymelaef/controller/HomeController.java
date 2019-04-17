package com.coderbd.webmvcthymelaef.controller;

import com.coderbd.webmvcthymelaef.entity.User;
import com.coderbd.webmvcthymelaef.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    private UserRepo repo;


    @GetMapping(value = "/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("user",new User());
        mv.setViewName("index");
        return mv;
    }

    @PostMapping(value = "/")
    public ModelAndView add(@Valid User user) {
        ModelAndView mv = new ModelAndView();
        if (user.getName() != null) {
            this.repo.save(user);
            System.out.println("success");
            mv.addObject("successMsg", "Successfully Saved");

        }
        mv.setViewName("index");
        return mv;
    }
}
