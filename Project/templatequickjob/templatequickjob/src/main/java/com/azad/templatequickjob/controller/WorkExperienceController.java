package com.azad.templatequickjob.controller;


import com.azad.templatequickjob.entity.User;
import com.azad.templatequickjob.entity.WorkExperience;
import com.azad.templatequickjob.repo.UserRepo;
import com.azad.templatequickjob.repo.WorkExperienceRepo;
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
@RequestMapping(value = "/workexperience/")
public class WorkExperienceController {

    @Autowired
    private WorkExperienceRepo repo;
    @Autowired
    private UserRepo userRepo;

    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("workExperience", new WorkExperience());
        model.addAttribute("userlist", this.userRepo.findAll());

        return "workexperiences/add";
    }

    @PostMapping(value = "add")
    public String add(@Valid WorkExperience workExperience, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "workexperiences/add";
        }
            Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
            User user=userRepo.findByUserName(authentication.getName());
            workExperience.setUser(user);
            this.repo.save(workExperience);
            model.addAttribute("userlist", this.userRepo.findAll());

            model.addAttribute("workExperience",new WorkExperience());
            model.addAttribute("successMsg", "Successfully Saved!");


        return "workexperiences/add";
    }

    @GetMapping("edit/{id}")
    public String editWorkExperienceView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("workExperience", this.repo.getOne(id));
        model.addAttribute("userlist", this.userRepo.findAll());

        return "workexperiences/edit";
    }

    @PostMapping("edit/{id}")
    public String editWorkExperience(@Valid WorkExperience workExperience, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userlist", this.userRepo.findAll());

            return "workexperiences/edit";
        } else {
            if (workExperience != null) {
               // Optional<WorkExperience> workExperience1 = this.repo.findByCityName(city.getCityName());

                Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
                User user=userRepo.findByUserName(authentication.getName());
                workExperience.setUser(user);
                    workExperience.setJobTitle(workExperience.getJobTitle());

                    this.repo.save(workExperience);
                    model.addAttribute("workExperience", new WorkExperience());
                    model.addAttribute("successMsg", "Edit Success ");
                }

            }
                model.addAttribute("userlist", this.userRepo.findAll());

        return "redirect:/workexperience/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/workexperience/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        User user=userRepo.findByUserName(auth.getName());
        model.addAttribute("list", this.repo.findAllByUser(user));
        return "workexperiences/list";
    }


}
