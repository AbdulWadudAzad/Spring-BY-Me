package com.coderbd.controller;

import com.coderbd.entity.Role;
import com.coderbd.entity.User;
import com.coderbd.repo.RoleRepo;
import com.coderbd.repo.UserRepo;
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
        roles.add(new Role(3L));
        roles.add(new Role(5L));
        User user = new User("Arefin", "sadmin", passwordEncoder.encode("1234"), "arefin@gmail.com", true, roles);
        repo.save(user);

        Set<Role> roles2 = new HashSet<>();
        roles2.add(new Role(4L));
        User user2 = new User("Jobayer", "jobayer", passwordEncoder.encode("1234"), "jobayer@gmail.com", true, roles2);
        repo.save(user2);

        Set<Role> roles3 = new HashSet<>();
        roles3.add(new Role(5L));
        User user3 = new User("Mahbub", "mahbub", passwordEncoder.encode("1234"), "mahbub@gmail.com", true, roles3);
        repo.save(user3);
      return "success";
    }
}
