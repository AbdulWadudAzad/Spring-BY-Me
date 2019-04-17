package com.azad.templatequickjob.controller;



import com.azad.templatequickjob.entity.YearExperience;
import com.azad.templatequickjob.repo.YearExperienceRepo;
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
@RequestMapping(value = "/yearexperience/")
public class YearExperienceController {

    @Autowired
    private YearExperienceRepo repo;

    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("yearExperience", new YearExperience());
        return "yearexperiences/add";
    }

    @PostMapping(value = "add")
    public String add(@Valid YearExperience yearExperience, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "yearexperiences/add";
        }
        if (repo.existsYearExperienceByYear(yearExperience.getYear())) {
            model.addAttribute("rejectMsg", "Already Have This Entry");
        } else {
            yearExperience.setYear(yearExperience.getYear());
            this.repo.save(yearExperience);
            model.addAttribute("yearExperience",new YearExperience());
            model.addAttribute("successMsg", "Successfully Saved!");
        }

        return "yearexperiences/add";
    }

    @GetMapping("edit/{id}")
    public String editYearExperienceView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("yearExperience", this.repo.getOne(id));
        return "yearexperiences/edit";
    }

    @PostMapping("edit/{id}")
    public String editYearExperience(@Valid YearExperience yearExperience, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "yearexperiences/edit";
        } else {
            if (yearExperience != null) {


                YearExperience yearExperience1 = this.repo.findByYear(yearExperience.getYear());
                if (yearExperience1 != null) {
                    model.addAttribute("existMSG", "Year is exist");
                    return "yearexperiences/edit";
                } else {
                    yearExperience.setYear(yearExperience.getYear());

                    this.repo.save(yearExperience);
                    model.addAttribute("yearExperience", new YearExperience());
                    model.addAttribute("successMsg", "Edit Success ");
                }

            }
        }

        return "redirect:/yearexperience/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/yearexperience/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        model.addAttribute("list", this.repo.findAll());
        return "yearexperiences/list";
    }


}
