package com.azad.templatequickjob.controller;


import com.azad.templatequickjob.entity.Skill;
import com.azad.templatequickjob.repo.SkillRepo;
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
@RequestMapping(value = "/skill/")
public class SkillController {

    @Autowired
    private SkillRepo repo;

    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("skill", new Skill());
        return "skills/add";
    }

    @PostMapping(value = "add")
    public String add(@Valid Skill skill, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "skills/add";
        }
        if (repo.existsSkillBySkillName(skill.getSkillName())) {
            model.addAttribute("rejectMsg", "Already Have This Entry");
        } else {
            skill.setSkillName(skill.getSkillName());
            this.repo.save(skill);
            model.addAttribute("skill",new Skill());
            model.addAttribute("successMsg", "Successfully Saved!");
        }

        return "skills/add";
    }

    @GetMapping("edit/{id}")
    public String editSkillView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("skill", this.repo.getOne(id));
        return "skills/edit";
    }

    @PostMapping("edit/{id}")
    public String editSkill(@Valid Skill skill, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "skills/edit";
        } else {
            if (skill != null) {
               // Optional<Skill> skill1 = this.repo.findBySkillName(skill.getSkillName());

                Skill skill1 = this.repo.findBySkillName(skill.getSkillName());
                if (skill1 != null) {
                    model.addAttribute("existMSG", "Skill is exist");
                    return "skills/edit";
                } else {
                    skill.setSkillName(skill.getSkillName());

                    this.repo.save(skill);
                    model.addAttribute("skill", new Skill());
                    model.addAttribute("successMsg", "Edit Success ");
                }

            }
        }

        return "redirect:/skill/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/skill/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        model.addAttribute("list", this.repo.findAll());
        return "skills/list";
    }


}
