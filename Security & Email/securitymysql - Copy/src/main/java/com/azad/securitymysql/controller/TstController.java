package com.azad.securitymysql.controller;

import com.azad.securitymysql.entity.User;
import com.azad.securitymysql.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class TstController {
    @Autowired
    private UserRepo repo;

    @ResponseBody
    @GetMapping(value = "/test")
    public User show() {
        return repo.findByUsernameOrName("abdul", "abdul azad");
    }
}
