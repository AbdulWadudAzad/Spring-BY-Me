package com.azad.templatequickjob.controller;

import com.azad.templatequickjob.entity.JobTitle;
import com.azad.templatequickjob.entity.Role;
import com.azad.templatequickjob.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(value = "/role/")
public class RoleController {

    @Autowired
    private RoleRepo repo;

    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("role", new Role());
        return "roles/add";
    }

    @PostMapping(value = "add")
    public String add(@Valid Role role, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "roles/add";
        }
        if (repo.existsRoleByRoleName(role.getRoleName())) {
            model.addAttribute("rejectMsg", "Already Have This Entry");
        } else {
            role.setRoleName(role.getRoleName().toUpperCase());
            this.repo.save(role);
            model.addAttribute("role",new Role());
            model.addAttribute("successMsg", "Successfully Saved!");
        }

        return "roles/add";
    }

    @GetMapping("edit/{id}")
    public String editROleView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("role", this.repo.getOne(id));
        return "roles/edit";
    }

    @PostMapping("edit/{id}")
    public String editROle(@Valid Role role, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "roles/edit";
        } else {
            if (role != null) {
               // Optional<Role> role1 = this.repo.findByRoleName(role.getRoleName());

                Role role1 = this.repo.findByRoleName(role.getRoleName());
                if (role1 != null) {
                    model.addAttribute("existMSG", "Role is exist");
                    return "roles/edit";
                } else {
                    role.setRoleName(role.getRoleName().toUpperCase());

                    this.repo.save(role);
                    model.addAttribute("role", new Role());
                    model.addAttribute("successMsg", "Edit Success ");
                }

            }
        }

        return "redirect:/role/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/role/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        model.addAttribute("list", this.repo.findAll());
        return "roles/list";
    }

    //use it first time add role
//    @GetMapping(value = "/role-save")
//    public String saveRole(){
//        Role role=new Role();
//        role.setRoleName("SUPERADMIN");
//        repo.save(role);
//
//        Role role2=new Role();
//        role2.setRoleName("ADMIN");
//        repo.save(role2);
//
//        Role role3=new Role();
//        role3.setRoleName("USER");
//        repo.save(role3);
//
//       return "success";
//    }
}
