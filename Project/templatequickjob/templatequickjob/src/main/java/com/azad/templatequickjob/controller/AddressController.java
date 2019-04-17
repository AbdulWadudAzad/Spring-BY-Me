package com.azad.templatequickjob.controller;


import com.azad.templatequickjob.entity.*;
import com.azad.templatequickjob.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/address/")
public class AddressController {

    @Autowired
    private AddressRepo repo;
    @Autowired
    private CountryRepo countryRepo;
    @Autowired
    private DivisionRepo divisionRepo;
    @Autowired
    private DistricRepo districRepo;
    @Autowired
    private CityRepo cityRepo;
    @Autowired
    private UserRepo userRepo;

//    @GetMapping(value = "/profile")
//    public String profileView(Model model) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        model.addAttribute("username",auth.getName());
//      Address address=this.repo.findByCountry_CountryName(auth.getName());
//        model.addAttribute("name", user.getFirstName());
//        model.addAttribute("user", user);
//
//        return "userprofile/profile";
//
//    }


    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("countryname", auth.getName());
        Address address = this.repo.findByCountry_CountryName(auth.getName());


        model.addAttribute("address", new Address());
        model.addAttribute("countrylist", this.countryRepo.findAll());
        model.addAttribute("divisionlist", this.divisionRepo.findAll());
        model.addAttribute("districlist", this.districRepo.findAll());
        model.addAttribute("citylist", this.cityRepo.findAll());
        model.addAttribute("userlist", this.userRepo.findAll());

        return "addresses/add";
    }

    @PostMapping(value = "add")
    public String add(@Valid Address address, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addresses/add";
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepo.findByUserName(authentication.getName());
            address.setUser(user);

            this.repo.save(address);
            model.addAttribute("countrylist", this.countryRepo.findAll());
            model.addAttribute("divisionlist", this.divisionRepo.findAll());
            model.addAttribute("districlist", this.districRepo.findAll());
            model.addAttribute("citylist", this.cityRepo.findAll());
            model.addAttribute("userlist", this.userRepo.findAll());

            model.addAttribute("address", new Address());
            model.addAttribute("successMsg", "Successfully Saved!");
        }

        return "addresses/add";
    }

    @GetMapping(value = "edit/{id}")
    public String viewEdit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("address", repo.getOne(id));
        model.addAttribute("countrylist", this.countryRepo.findAll());
        model.addAttribute("divisionlist", this.divisionRepo.findAll());
        model.addAttribute("districlist", this.districRepo.findAll());
        model.addAttribute("citylist", this.cityRepo.findAll());
        model.addAttribute("userlist", this.userRepo.findAll());


        return "addresses/edit";
    }

    @PostMapping("edit/{id}")
    public String editCity(@Valid Address address, BindingResult bindingResult, Model model, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("countrylist", this.countryRepo.findAll());
            model.addAttribute("divisionlist", this.divisionRepo.findAll());
            model.addAttribute("districlist", this.districRepo.findAll());
            model.addAttribute("citylist", this.cityRepo.findAll());
            model.addAttribute("userlist", this.userRepo.findAll());


            return "addresses/edit";
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepo.findByUserName(authentication.getName());
            address.setUser(user);
            this.repo.save(address);
            model.addAttribute("address", new Address());
            model.addAttribute("successMsg", "Edit Success ");
        }

        model.addAttribute("countrylist", this.countryRepo.findAll());
        model.addAttribute("divisionlist", this.divisionRepo.findAll());
        model.addAttribute("districlist", this.districRepo.findAll());
        model.addAttribute("citylist", this.cityRepo.findAll());
        model.addAttribute("userlist", this.userRepo.findAll());


        return "redirect:/address/list";
    }


    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/address/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findByUserName(auth.getName());
        model.addAttribute("list", this.repo.findAllByUser(user));
        return "addresses/list";
    }


}

