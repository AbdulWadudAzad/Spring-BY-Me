package com.azad.templatequickjob.controller;

import com.azad.templatequickjob.entity.Role;
import com.azad.templatequickjob.entity.User;
import com.azad.templatequickjob.repo.RoleRepo;
import com.azad.templatequickjob.repo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {
    @Autowired
    private UserRepo repo;

    @Autowired
    private RoleRepo roleRepo;

@Autowired
private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/user-save")
    public String saveUser() {

        Set<Role> roles=new HashSet<>();
        roles.add(new Role("SUPERADMIN"));
        roles.add(new Role("ADMIN"));
      roles.forEach((role -> {
          roleRepo.save(role);
      }));

        roles.add(new Role(1L));
        roles.add(new Role(2L));
        User user = new User("Wadud","Azad", "azad", passwordEncoder.encode("Azad123"), "azadmiu37@gmail.com", true,null, roles);
        repo.save(user);
      return "success";
    }

    @GetMapping(value = "/register")
    public ModelAndView displayRegister(User user){
        ModelAndView mv=new ModelAndView();
        mv.addObject("user",user);
        mv.setViewName("signup");
        return mv;
    }
}
