package com.azad.templatequickjob.controller;

import com.azad.templatequickjob.entity.User;
import com.azad.templatequickjob.repo.RoleRepo;
import com.azad.templatequickjob.repo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping(value = "/user/")
public class UserController {
    @Autowired
    private UserRepo repo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "add")
    public String viewAdd(User user, Model model){
        model.addAttribute("rolelist", this.roleRepo.findAll());
        return "users/add";
    }
    @PostMapping(value = "add")
    public String add(@Valid User user, BindingResult result, Model model){
        if(result.hasErrors()){
           model.addAttribute("rolelist", this.roleRepo.findAll());
            return "users/add";
        }
        if(repo.existsByEmail(user.getEmail())){
            model.addAttribute("rejectMsg","Already Have This Entry");
        }else{
            user.setRegiDate(new Date());

            String username = user.getEmail().split("\\@")[0];
            user.setUserName(username);
            user.setEnabled(true);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setConfirmationToken(UUID.randomUUID().toString());

            this.repo.save(user);
            model.addAttribute("user", new User());
            model.addAttribute("successMsg","Successfully Saved!");
            model.addAttribute("rolelist", this.roleRepo.findAll());
        }
   //     model.addAttribute( this.roleRepo.findAll());
        return "users/add";
    }
    @GetMapping(value = "edit/{id}")
    public String viewEdit(Model model, @PathVariable("id") Long id){
        model.addAttribute("user",repo.getOne(id));
        model.addAttribute("rolelist", this.roleRepo.findAll());
        return "users/edit";
    }
/*    @PostMapping(value = "edit/{id}")
    public String edit(@Valid User user, BindingResult result, Model model,@PathVariable("id") Long id){
        if(result.hasErrors()){
            return "edit2";
        }
        Optional<User> u = this.repo.findByEmail(user.getEmail());
        if(u.get().getId() != id){
            model.addAttribute("rejectMsg","Already Have This Entry");
            return "edit2";
        }else{
            this.repo.save(user);
            model.addAttribute("user", new User());
            model.addAttribute("rolelist", this.roleRepo.findAll());
        }

        return "redirect:/user/list";
    }*/
    @PostMapping("edit/{id}")
    public String editCity(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "users/edit";
        } else {
            if (user != null) {
                User user1 = this.repo.findByEmail(user.getEmail());
                if (user1 != null) {
                    model.addAttribute("existMSG", "Data already exist");
                    return "users/edit";
                } else {
                    user.setEmail(user.getEmail());

                    this.repo.save(user);
                    model.addAttribute("division", new User());
                    model.addAttribute("successMsg", "Edit Success ");
                }

            }
        }

        return "redirect:/user/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id){
        if(id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/user/list";
    }

    @GetMapping(value = "list")
    public String list(Model model){
        model.addAttribute("list", this.repo.findAll());
        return "users/list";
    }



























//    @GetMapping(value = "/user-save")
//    public String saveUser() {
//
//        Set<Role> roles=new HashSet<>();
//        roles.add(new Role("SUPERADMIN"));
//        roles.add(new Role("ADMIN"));
//      roles.forEach((role -> {
//          roleRepo.save(role);
//      }));
//
//        roles.add(new Role(1L));
//        roles.add(new Role(2L));
//        User user = new User("Wadud","Azad", "azad", passwordEncoder.encode("Azad123"), "azadmiu37@gmail.com", true,null, roles);
//        repo.save(user);
//      return "success";
//    }
//
//    @GetMapping(value = "/register")
//    public ModelAndView displayRegister(User user){
//        ModelAndView mv=new ModelAndView();
//        mv.addObject("user",user);
//        mv.setViewName("signup");
//        return mv;
//    }
}
