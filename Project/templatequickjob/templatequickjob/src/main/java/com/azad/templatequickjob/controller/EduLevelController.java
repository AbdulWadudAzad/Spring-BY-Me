package com.azad.templatequickjob.controller;


import com.azad.templatequickjob.entity.EduLevel;
import com.azad.templatequickjob.repo.EduLevelRepo;
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
@RequestMapping(value = "/edulevel/")
public class EduLevelController {

    @Autowired
    private EduLevelRepo repo;

    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("eduLevel", new EduLevel());
        return "edulevels/add";
    }

    @PostMapping(value = "add")
    public String add(@Valid EduLevel eduLevel, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edulevels/add";
        }
        if (repo.existsEduLevelByLevelName(eduLevel.getLevelName())) {
            model.addAttribute("rejectMsg", "Already Have This Entry");
        } else {
            eduLevel.setLevelName(eduLevel.getLevelName());
            this.repo.save(eduLevel);
            model.addAttribute("eduLevel",new EduLevel());
            model.addAttribute("successMsg", "Successfully Saved!");
        }

        return "edulevels/add";
    }

    @GetMapping("edit/{id}")
    public String editEduLevelView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("eduLevel", this.repo.getOne(id));
        return "edulevels/edit";
    }

    @PostMapping("edit/{id}")
    public String editEduLevel(@Valid EduLevel eduLevel, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edulevels/edit";
        } else {
            if (eduLevel != null) {
               // Optional<EduLevel> eduLevel1 = this.repo.findByLevelName(eduLevel.getLevelName());

                EduLevel eduLevel1 = this.repo.findByLevelName(eduLevel.getLevelName());
                if (eduLevel1 != null) {
                    model.addAttribute("existMSG", "Education Level is exist");
                    return "edulevels/edit";
                } else {
                    eduLevel.setLevelName(eduLevel.getLevelName());

                    this.repo.save(eduLevel);
                    model.addAttribute("eduLevel", new EduLevel());
                    model.addAttribute("successMsg", "Edit Success ");
                }

            }
        }

        return "redirect:/edulevel/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/edulevel/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        model.addAttribute("list", this.repo.findAll());
        return "edulevels/list";
    }


}
