package com.azad.templatequickjob.controller;



import com.azad.templatequickjob.entity.EduSubject;
import com.azad.templatequickjob.repo.EduSubjectRepo;
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
@RequestMapping(value = "/edusubject/")
public class EduSubjectController {

    @Autowired
    private EduSubjectRepo repo;

    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("eduSubject", new EduSubject());
        return "edusubjects/add";
    }

    @PostMapping(value = "add")
    public String add(@Valid EduSubject eduSubject, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edusubjects/add";
        }
        if (repo.existsEduSubjectBySubName(eduSubject.getSubName())) {
            model.addAttribute("rejectMsg", "Already Have This Entry");
        } else {
            eduSubject.setSubName(eduSubject.getSubName());
            this.repo.save(eduSubject);
            model.addAttribute("eduSubject",new EduSubject());
            model.addAttribute("successMsg", "Successfully Saved!");
        }

        return "edusubjects/add";
    }

    @GetMapping("edit/{id}")
    public String editedusubjectView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("eduSubject", this.repo.getOne(id));
        return "edusubjects/edit";
    }

    @PostMapping("edit/{id}")
    public String editedusubject(@Valid EduSubject eduSubject, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edusubjects/edit";
        } else {
            if (eduSubject != null) {
               // Optional<EduSubject> eduSubject1 = this.repo.findBySubName(eduSubject.getSubName());

                EduSubject eduSubject1 = this.repo.findBySubName(eduSubject.getSubName());
                if (eduSubject1 != null) {
                    model.addAttribute("existMSG", "Education Subject is exist");
                    return "edusubjects/edit";
                } else {
                    eduSubject.setSubName(eduSubject.getSubName());

                    this.repo.save(eduSubject);
                    model.addAttribute("eduSubject", new EduSubject());
                    model.addAttribute("successMsg", "Edit Success ");
                }

            }
        }

        return "redirect:/edusubject/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/edusubject/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        model.addAttribute("list", this.repo.findAll());
        return "edusubjects/list";
    }


}
