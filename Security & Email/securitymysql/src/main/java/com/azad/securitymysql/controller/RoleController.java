package com.azad.securitymysql.controller;


import com.azad.securitymysql.entity.Role;
import com.azad.securitymysql.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;


@Controller
public class RoleController {
    @Autowired
    private RoleRepo repo;

    @GetMapping(value = "/addrole")
    public String addROleView(Role role) {
        return "role/add";
    }


    @PostMapping(value = "/addrole")
    public String save(@Valid Role role, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "role/add";
        } else {
            if(role !=null){
                Role role1=this.repo.findByRoleName(role.getRoleName());
                if(role1 !=null){
                    model.addAttribute("existMsg","Role is already exist");

                }else {
                    this.repo.save(role);
                    model.addAttribute("role",new Role());
                    model.addAttribute("successMsg","Role is created");
                }
            }


        }
        return "role/add";
    }
    @RequestMapping(value = "/deleterole/{id}", method = RequestMethod.GET)
    public String delRole(@PathVariable ("id") Long id){
        this.repo.deleteById(id);
        return "redirect:/rolelist";
    }
    @GetMapping("/editrole/{id}")
    public String editROleView(@PathVariable("id") Long id,Model model){
        model.addAttribute("role", this.repo.getOne(id))  ;
        return "role/edit";
    }

    @PostMapping("/editrole/{id}")
    public String editROle(@Valid Role role,BindingResult bindingResult,Model model){
        if (bindingResult.hasErrors()){
            return "role/edit";
        }else {
            if (role!=null){
                Role role1=this.repo.findByRoleName(role.getRoleName()) ;
                if(role1 !=null){
                    model.addAttribute("existMsg","Role is exist");
                    return "role/edit";
                }else {
                    this.repo.save(role);
                    model.addAttribute("role",new Role());
                    model.addAttribute("successMsg","Edit Success ");
                }

            }
        }

        return "redirect:/rolelist";
    }


    @GetMapping(value = "/rolelist")
    public String roleList(Model model) {
        model.addAttribute("list", this.repo.findAll());
        return "role/list";
    }



}
