package com.azad.templatequickjob.controller;

import com.azad.templatequickjob.entity.CompanyIndustry;
import com.azad.templatequickjob.repo.CompanyIndustryRepo;
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
@RequestMapping(value = "/companyindustry/")
public class CompanyIndustryController {

    @Autowired
    private CompanyIndustryRepo repo;

    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("companyIndustry", new CompanyIndustry());
        return "companyindustrys/add";
    }

    @PostMapping(value = "add")
    public String add(@Valid CompanyIndustry companyIndustry, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "companyindustrys/add";
        }
        if (repo.existsCompanyIndustryByComIndustry(companyIndustry.getComIndustry())) {
            model.addAttribute("rejectMsg", "Already Have This Entry");
        } else {
            companyIndustry.setComIndustry(companyIndustry.getComIndustry());
            this.repo.save(companyIndustry);
            model.addAttribute("companyIndustry",new CompanyIndustry());
            model.addAttribute("successMsg", "Successfully Saved!");
        }

        return "companyindustrys/add";
    }

    @GetMapping("edit/{id}")
    public String editCompanyIndustryView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("companyIndustry", this.repo.getOne(id));
        return "companyindustrys/edit";
    }

    @PostMapping("edit/{id}")
    public String editCompanyIndustry(@Valid CompanyIndustry companyIndustry, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "companyindustrys/edit";
        } else {
            if (companyIndustry != null) {
               // Optional<CompanyIndustry> companyIndustry1 = this.repo.findByComIndustry(companyIndustry.getComIndustry());

                CompanyIndustry companyIndustry1 = this.repo.findByComIndustry(companyIndustry.getComIndustry());
                if (companyIndustry1 != null) {
                    model.addAttribute("existMSG", "Company Industry is exist");
                    return "companyindustrys/edit";
                } else {
                    companyIndustry.setComIndustry(companyIndustry.getComIndustry());

                    this.repo.save(companyIndustry);
                    model.addAttribute("companyIndustry", new CompanyIndustry());
                    model.addAttribute("successMsg", "Edit Success ");
                }

            }
        }

        return "redirect:/companyindustry/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/companyindustry/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        model.addAttribute("list", this.repo.findAll());
        return "companyindustrys/list";
    }


}
