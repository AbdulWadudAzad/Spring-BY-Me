package com.azad.securitymysql.controller;

import com.azad.securitymysql.entity.Role;
import com.azad.securitymysql.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class RoleController {
    @Autowired
    private RoleRepo repo;

    @GetMapping(value = "/role-save")
    public String saveRole() {
        Role role = new Role();
        role.setRoleName("SUPERADMIN");
        repo.save(role);

        Role role2 = new Role();
        role2.setRoleName("ADMIN");
        repo.save(role2);

        Role role3 = new Role();
        role3.setRoleName("USER");
        repo.save(role3);

        return "success";
    }

}
