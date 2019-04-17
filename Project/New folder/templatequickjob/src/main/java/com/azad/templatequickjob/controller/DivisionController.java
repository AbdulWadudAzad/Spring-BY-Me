package com.azad.templatequickjob.controller;



import com.azad.templatequickjob.entity.Division;
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
import java.util.Optional;

@Controller
@RequestMapping(value = "/division/")
public class DivisionController {

    @Autowired
    private DivisionRepo repo;

    @GetMapping(value = "add")
    public String viewAdd(Model model){
        model.addAttribute("division",new Division());
        return "divisions/add";
    }
    @PostMapping(value = "add")
    public String add(@Valid Division division, BindingResult result, Model model){
        if(result.hasErrors()){
            return "divisions/add";
        }
        if(repo.existsDivisionByDivisionName(division.getDivisionName())){
            model.addAttribute("rejectMsg","Already Have This Entry");
        }else{
            division.setDivisionName(division.getDivisionName().toUpperCase());
            this.repo.save(division);
            model.addAttribute("successMsg","Successfully Saved!");
        }

        return "divisions/add";
    }
    @GetMapping(value = "edit/{id}")
    public String viewEdit(Model model, @PathVariable("id") Long id){
        model.addAttribute("division",repo.getOne(id));
        return "divisions/edit";
    }
    @PostMapping("edit/{id}")
    public String editCity(@Valid Division division, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "citys/edit";
        } else {
            if (division != null) {
                // Optional<City> city1 = this.repo.findByCityName(city.getCityName());

                Division division1 = this.repo.findByDivisionName(division.getDivisionName());
                if (division1 != null) {
                    model.addAttribute("existMSG", "Division is exist");
                    return "citys/edit";
                } else {
                    division.setDivisionName(division.getDivisionName());

                    this.repo.save(division);
                    model.addAttribute("division", new Division());
                    model.addAttribute("successMsg", "Edit Success ");
                }

            }
        }

        return "redirect:/city/list";
    }


    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id){
        if(id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/division/list";
    }

    @GetMapping(value = "list")
    public String list(Model model){
        model.addAttribute("list",this.repo.findAll());
        return "divisions/list";
    }

}

