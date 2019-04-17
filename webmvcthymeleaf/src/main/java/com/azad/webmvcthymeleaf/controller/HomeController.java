package com.azad.webmvcthymeleaf.controller;

import com.azad.webmvcthymeleaf.entity.User;
import com.azad.webmvcthymeleaf.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.soap.SOAPBinding;
import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    private UserRepo repo;

    @GetMapping(value = "/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", new User());
        mv.setViewName("index");
        return mv;
    }

    @PostMapping(value = "/")
    public ModelAndView add(@Valid User user) {
        ModelAndView mv = new ModelAndView();
        if (user.getNeme() != null) {
            this.repo.seve(user);
            mv.addObject("successMsg", "SuccessFully save");
            mv.addObject("user", new User());
        }
        mv.setViewName("index");
        return mv;
    }

}
