package com.azad.securitymysql.controller;


import com.azad.securitymysql.entity.User;
import com.azad.securitymysql.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    private UserRepo repo;


    @GetMapping(value ={ "/user"})
    public String userView(){
        return "user/user";
    }

    @GetMapping(value ={ "/se"})
    public String secureView(Model model){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username",auth.getName());
        User user=repo.findByUsername(auth.getName());
        model.addAttribute("name",user.getName());

        return "secure/sec";
    }

    @GetMapping(value ={ "/adm"})
    public String adminView(){
        return "admin/home";
    }



}