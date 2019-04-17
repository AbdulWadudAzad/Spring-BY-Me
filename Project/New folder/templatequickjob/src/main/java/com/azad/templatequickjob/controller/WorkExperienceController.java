package com.azad.templatequickjob.controller;


import com.azad.templatequickjob.entity.WorkExperience;
import com.azad.templatequickjob.repo.WorkExperienceRepo;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("workExperience", new WorkExperience());
        return "workexperiences/add";
    }

    @PostMapping(value = "add")
    public String add(@Valid WorkExperience workExperience, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "workexperiences/add";
        }
        if (repo.existsWorkExperienceByYear(workExperience.getYear())) {
            model.addAttribute("rejectMsg", "Already Have This Entry");
        } else {
            workExperience.setYear(workExperience.getYear());
            this.repo.save(workExperience);
            model.addAttribute("workExperience",new WorkExperience());
            model.addAttribute("successMsg", "Successfully Saved!");
        }

        return "workexperiences/add";
    }

    @GetMapping("edit/{id}")
    public String editWorkExperienceView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("workExperience", this.repo.getOne(id));
        return "workexperiences/edit";
    }

    @PostMapping("edit/{id}")
    public String editWorkExperience(@Valid WorkExperience workExperience, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "workexperiences/edit";
        } else {
            if (workExperience != null) {
               // Optional<WorkExperience> workExperience1 = this.repo.findByCityName(city.getCityName());

                WorkExperience workExperience1 = this.repo.findByYear(workExperience.getYear());
                if (workExperience1 != null) {
                    model.addAttribute("existMSG", "Work Experience is exist");
                    return "workexperiences/edit";
                } else {
                    workExperience.setYear(workExperience.getYear());

                    this.repo.save(workExperience);
                    model.addAttribute("workExperience", new WorkExperience());
                    model.addAttribute("successMsg", "Edit Success ");
                }

            }
        }

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
        model.addAttribute("list", this.repo.findAll());
        return "workexperiences/list";
    }


}
