package com.azad.templatequickjob.controller;

import com.azad.templatequickjob.entity.City;
import com.azad.templatequickjob.entity.Distric;
import com.azad.templatequickjob.repo.CityRepo;
import com.azad.templatequickjob.repo.DistricRepo;
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
@RequestMapping(value = "/city/")
public class CityController {
    @Autowired
    private DistricRepo districRepo;
    @Autowired
    private CityRepo repo;

    @GetMapping(value = "add")
    public String viewAdd(Model model){
        model.addAttribute("city",new City());
        model.addAttribute("districlist", this.districRepo.findAll());
        return "citys/add";
    }
    @PostMapping(value = "add")
    public String add(@Valid City city, BindingResult result, Model model){
        if(result.hasErrors()){
            return "citys/add";
        }
        else{
            city.setCityName(city.getCityName());
            this.repo.save(city);
            model.addAttribute("districlist", this.districRepo.findAll());
            model.addAttribute("city",new City());
            model.addAttribute("successMsg","Successfully Saved!");
        }

        return "citys/add";
    }
    @GetMapping(value = "edit/{id}")
    public String viewEdit(Model model, @PathVariable("id") Long id){
        model.addAttribute("city",repo.getOne(id));
        model.addAttribute("districlist", this.districRepo.findAll());

        return "citys/edit";
    }
    @PostMapping("edit/{id}")
    public String editCity(@Valid City city, BindingResult bindingResult, Model model,@PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("districlist", this.districRepo.findAll());
            return "citys/edit";
        }else {
            city.setId(id);
            this.repo.save(city);
            model.addAttribute("city", new City());
            model.addAttribute("successMsg", "Edit Success ");
        }

        model.addAttribute("districlist", this.districRepo.findAll());

        return "redirect:/city/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/city/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        model.addAttribute("list", this.repo.findAll());
        return "citys/list";
    }


}
