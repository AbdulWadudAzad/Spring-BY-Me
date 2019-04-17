package com.azad.templatequickjob.controller;

import com.azad.templatequickjob.entity.City;
import com.azad.templatequickjob.repo.CityRepo;
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
    private CityRepo repo;

    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("city", new City());
        return "citys/add";
    }

    @PostMapping(value = "add")
    public String add(@Valid City city, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "citys/add";
        }
        if (repo.existsCityByCityName(city.getCityName())) {
            model.addAttribute("rejectMsg", "Already Have This Entry");
        } else {
            city.setCityName(city.getCityName());
            this.repo.save(city);
            model.addAttribute("city",new City());
            model.addAttribute("successMsg", "Successfully Saved!");
        }

        return "citys/add";
    }

    @GetMapping("edit/{id}")
    public String editCityView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("city", this.repo.getOne(id));
        return "citys/edit";
    }

    @PostMapping("edit/{id}")
    public String editCity(@Valid City city, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "citys/edit";
        } else {
            if (city != null) {
               // Optional<City> city1 = this.repo.findByCityName(city.getCityName());

                City city1 = this.repo.findByCityName(city.getCityName());
                if (city1 != null) {
                    model.addAttribute("existMSG", "City is exist");
                    return "citys/edit";
                } else {
                    city.setCityName(city.getCityName());

                    this.repo.save(city);
                    model.addAttribute("city", new City());
                    model.addAttribute("successMsg", "Edit Success ");
                }

            }
        }

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
