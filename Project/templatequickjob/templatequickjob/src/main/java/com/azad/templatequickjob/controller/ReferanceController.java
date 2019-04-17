package com.azad.templatequickjob.controller;


import com.azad.templatequickjob.entity.Referance;
import com.azad.templatequickjob.entity.User;
import com.azad.templatequickjob.repo.ReferanceRepo;
import com.azad.templatequickjob.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/referance/")
public class ReferanceController {

    @Autowired
    private ReferanceRepo repo;
    @Autowired
    private UserRepo userRepo;

    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("referance", new Referance());
        model.addAttribute("userlist", this.userRepo.findAll());

        return "referances/add";
    }

    @PostMapping(value = "add")
    public String add(@Valid Referance referance, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "referances/add";
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepo.findByUserName(authentication.getName());
            referance.setUser(user);
            this.repo.save(referance);
            model.addAttribute("userlist", this.userRepo.findAll());

            model.addAttribute("referance", new Referance());
            model.addAttribute("successMsg", "Successfully Saved!");
        }

        return "referances/add";
    }

    @GetMapping("edit/{id}")
    public String editReferanceView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("referance", this.repo.getOne(id));
        model.addAttribute("userlist", this.userRepo.findAll());

        return "referances/edit";
    }

    @PostMapping("edit/{id}")
    public String editReferance(@Valid Referance referance, BindingResult bindingResult, Model model, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userlist", this.userRepo.findAll());

            return "referances/edit";
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepo.findByUserName(authentication.getName());
            referance.setUser(user);
            this.repo.save(referance);
            model.addAttribute("successMsg", "Edit Success ");

        }
        model.addAttribute("userlist", this.userRepo.findAll());

        return "redirect:/referance/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/referance/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        User user=userRepo.findByUserName(auth.getName());
        model.addAttribute("list", this.repo.findAllByUser(user));
        return "referances/list";
    }


}
