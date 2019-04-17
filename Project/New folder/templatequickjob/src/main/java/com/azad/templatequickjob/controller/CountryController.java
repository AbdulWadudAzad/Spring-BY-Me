package com.azad.templatequickjob.controller;



import com.azad.templatequickjob.entity.Country;
import com.azad.templatequickjob.repo.CountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(value = "/country/")
public class CountryController {

    @Autowired
    private CountryRepo repo;

    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("country", new Country());
        return "countries/add";
    }

    @PostMapping(value = "add")
    public String add(@Valid Country country, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "countries/add";
        }
        if (repo.existsCountryByCountryName(country.getCountryName())) {
            model.addAttribute("rejectMsg", "Already Have This Entry");
        } else {
            country.setCountryName(country.getCountryName());
            this.repo.save(country);
            model.addAttribute("country",new Country());
            model.addAttribute("successMsg", "Successfully Saved!");
        }

        return "countries/add";
    }

    @GetMapping("edit/{id}")
    public String editCountryView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("country", this.repo.getOne(id));
        return "countries/edit";
    }

    @PostMapping("edit/{id}")
    public String editCountry(@Valid Country country, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "countries/edit";
        } else {
            if (country != null) {
                // Optional<Country> country1 = this.repo.findByCountryName(country.getCountryName());

                Country country1 = this.repo.findByCountryName(country.getCountryName());
                if (country1 != null) {
                    model.addAttribute("existMSG", "Country is exist");
                    return "countries/edit";
                } else {
                    country.setCountryName(country.getCountryName());

                    this.repo.save(country);
                    model.addAttribute("country", new Country());
                    model.addAttribute("successMsg", "Edit Success ");
                }

            }
        }

        return "redirect:/country/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/country/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        model.addAttribute("list", this.repo.findAll());
        return "countries/list";
    }


}
