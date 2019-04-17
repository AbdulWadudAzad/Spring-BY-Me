package com.azad.templatequickjob.controller;

import com.azad.templatequickjob.entity.EmploymentType;
import com.azad.templatequickjob.repo.EmploymentTypeRepo;
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
@RequestMapping(value = "/employmenttype/")
public class EmploymentTypeController {

    @Autowired
    private EmploymentTypeRepo repo;

    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("employmentType", new EmploymentType());
        return "employmenttypes/add";
    }

    @PostMapping(value = "add")
    public String add(@Valid EmploymentType employmentType, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "employmenttypes/add";
        }
        if (repo.existsEmploymentTypeByEmpType(employmentType.getEmpType())) {
            model.addAttribute("rejectMsg", "Already Have This Entry");
        } else {
            employmentType.setEmpType(employmentType.getEmpType());
            this.repo.save(employmentType);
            model.addAttribute("employmentType",new EmploymentType());
            model.addAttribute("successMsg", "Successfully Saved!");
        }

        return "employmenttypes/add";
    }

    @GetMapping("edit/{id}")
    public String editEmploymentTypeView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("employmentType", this.repo.getOne(id));
        return "employmenttypes/edit";
    }

    @PostMapping("edit/{id}")
    public String editEmploymentType(@Valid EmploymentType employmentType, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "employmenttypes/edit";
        } else {
            if (employmentType != null) {
               // Optional<City> city1 = this.repo.findByCityName(city.getCityName());

                EmploymentType emptype1 = this.repo.findByEmpType(employmentType.getEmpType());
                if (emptype1 != null) {
                    model.addAttribute("existMSG", "Employment Type is exist");
                    return "employmenttypes/edit";
                } else {
                    employmentType.setEmpType(employmentType.getEmpType());

                    this.repo.save(employmentType);
                    model.addAttribute("employmentType", new EmploymentType());
                    model.addAttribute("successMsg", "Edit Success ");
                }

            }
        }

        return "redirect:/employmenttype/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/employmenttype/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        model.addAttribute("list", this.repo.findAll());
        return "employmenttypes/list";
    }


}
