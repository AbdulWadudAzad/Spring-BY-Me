package com.azad.role.controller;

import com.azad.role.entity.Role;
import com.azad.role.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RoleController {
    @Autowired
    private RoleRepo repo;

    @GetMapping(value = "/addrole")
    public String addRoleView(Role role) {
        return "role/role/add";
    }

    @PostMapping(value = "/addrole")
    public String addRole(@Valid Role role, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "role/role/add";
        } else {
            this.repo.save(role);
            model.addAttribute("role", new Role());
            model.addAttribute("successMsq", "Role is created");
        }
        return "role/role/add";
    }
//    @GetMapping(value = "/addrole")
//    public String index(Model model) {
//        model.addAttribute("list", this.repo.findAll());
//        return "role/role/add";
//    }
}
