package com.azad.securitymysql.controller;

import com.azad.securitymysql.entity.Role;
import com.azad.securitymysql.entity.User;
import com.azad.securitymysql.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {
    @Autowired
    private UserRepo repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/user-save")
    public String saveUser() {
        Set<Role> roles = new HashSet<>();

        roles.add(new Role(1L));
        roles.add(new Role(3L));
        User user = new User("azad", passwordEncoder.encode("1234"), "abdul azad", "azad@gmail.com", roles);
        repo.save(user);

        Set<Role> roles2 = new HashSet<>();
        roles2.add(new Role(1L));
        User user21 = new User("wadud", passwordEncoder.encode("1234"), "wadud azad", "wadud@gmail.com", roles2);
        repo.save(user21);

        Set<Role> roles3 = new HashSet<>();
        roles3.add(new Role(2L));
        User user31 = new User("abdul", passwordEncoder.encode("1234"), "abdul azad", "abdul@gmail.com", roles3);
        repo.save(user31);


        return "success";
    }


//     this.username = username;
//        this.password = password;
//        this.name = name;
//        this.age = age;
//        this.email = email;
//        this.roles = roles;
//
}
