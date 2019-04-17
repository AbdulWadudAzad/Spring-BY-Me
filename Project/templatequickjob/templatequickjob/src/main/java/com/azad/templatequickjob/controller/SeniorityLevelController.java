package com.azad.templatequickjob.controller;


import com.azad.templatequickjob.entity.SeniorityLevel;
import com.azad.templatequickjob.repo.SeniorityLevelRepo;
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
@RequestMapping(value = "/senioritylevel/")
public class SeniorityLevelController {

    @Autowired
    private SeniorityLevelRepo repo;

    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("seniorityLevel", new SeniorityLevel());
        return "senioritylevels/add";
    }

    @PostMapping(value = "add")
    public String add(@Valid SeniorityLevel seniorityLevel, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "senioritylevels/add";
        }
        if (repo.existsSeniorityLevelBySeniority(seniorityLevel.getSeniority())) {
            model.addAttribute("rejectMsg", "Already Have This Entry");
        } else {
            seniorityLevel.setSeniority(seniorityLevel.getSeniority());
            this.repo.save(seniorityLevel);
            model.addAttribute("seniorityLevel",new SeniorityLevel());
            model.addAttribute("successMsg", "Successfully Saved!");
        }

        return "senioritylevels/add";
    }

    @GetMapping("edit/{id}")
    public String editSeniorityLevelView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("seniorityLevel", this.repo.getOne(id));
        return "senioritylevels/edit";
    }

    @PostMapping("edit/{id}")
    public String editSeniorityLevel(@Valid SeniorityLevel seniorityLevel, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "senioritylevels/edit";
        } else {
            if (seniorityLevel != null) {
               // Optional<SeniorityLevel> seniorityLevel1 = this.repo.findBySeniority(seniorityLevel.getSeniority());

                SeniorityLevel seniorityLevel1 = this.repo.findBySeniority(seniorityLevel.getSeniority());
                if (seniorityLevel1 != null) {
                    model.addAttribute("existMSG", "Seniority Level is exist");
                    return "senioritylevels/edit";
                } else {
                    seniorityLevel.setSeniority(seniorityLevel.getSeniority());

                    this.repo.save(seniorityLevel);
                    model.addAttribute("seniorityLevel", new SeniorityLevel());
                    model.addAttribute("successMsg", "Edit Success ");
                }

            }
        }

        return "redirect:/senioritylevel/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/senioritylevel/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        model.addAttribute("list", this.repo.findAll());
        return "senioritylevels/list";
    }


}
