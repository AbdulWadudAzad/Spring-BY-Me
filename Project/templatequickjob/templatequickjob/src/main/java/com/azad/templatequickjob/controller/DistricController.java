package com.azad.templatequickjob.controller;


import com.azad.templatequickjob.entity.Distric;

import com.azad.templatequickjob.entity.Division;
import com.azad.templatequickjob.repo.DistricRepo;
import com.azad.templatequickjob.repo.DivisionRepo;
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
@RequestMapping(value = "/distric/")
public class DistricController {
    @Autowired
    private DivisionRepo divisionRepo;
    @Autowired
    private DistricRepo repo;

    @GetMapping(value = "add")
    public String viewAdd(Model model){
        model.addAttribute("distric",new Distric());
        model.addAttribute("divisionlist", this.divisionRepo.findAll());
        return "districs/add";
    }
    @PostMapping(value = "add")
    public String add(@Valid Distric distric, BindingResult result, Model model){
        if(result.hasErrors()){
            return "districs/add";
        }
        else{
            distric.setDistricName(distric.getDistricName());
            this.repo.save(distric);
            model.addAttribute("divisionlist", this.divisionRepo.findAll());
            model.addAttribute("distric",new Distric());
            model.addAttribute("successMsg","Successfully Saved!");
        }

        return "districs/add";
    }
    @GetMapping(value = "edit/{id}")
    public String viewEdit(Model model, @PathVariable("id") Long id){
        model.addAttribute("distric",repo.getOne(id));
        model.addAttribute("divisionlist", this.divisionRepo.findAll());

        return "districs/edit";
    }
    @PostMapping("edit/{id}")
    public String editCity(@Valid Distric distric, BindingResult bindingResult, Model model,@PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("divisionlist", this.divisionRepo.findAll());
            return "districs/edit";
        }else {
            distric.setId(id);
            this.repo.save(distric);
            model.addAttribute("distric", new Distric());
            model.addAttribute("successMsg", "Edit Success ");
        }

        model.addAttribute("divisionlist", this.divisionRepo.findAll());

        return "redirect:/distric/list";
    }


    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/distric/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        model.addAttribute("list", this.repo.findAll());
        return "districs/list";
    }


}
