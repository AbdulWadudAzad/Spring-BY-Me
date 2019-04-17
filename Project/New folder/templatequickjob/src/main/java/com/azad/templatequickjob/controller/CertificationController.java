package com.azad.templatequickjob.controller;


import com.azad.templatequickjob.entity.Certification;
import com.azad.templatequickjob.repo.CertificationRepo;
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
@RequestMapping(value = "/certification/")
public class CertificationController {

    @Autowired
    private CertificationRepo repo;

    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("certification", new Certification());
        return "certifications/add";
    }

    @PostMapping(value = "add")
    public String add(@Valid Certification certification, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "certifications/add";
        }
        if (repo.existsCertificationByCertificName(certification.getCertificName())) {
            model.addAttribute("rejectMsg", "Already Have This Entry");
        } else {
            certification.setCertificName(certification.getCertificName());
            this.repo.save(certification);
            model.addAttribute("certification",new Certification());
            model.addAttribute("successMsg", "Successfully Saved!");
        }

        return "certifications/add";
    }

    @GetMapping("edit/{id}")
    public String editCertificationView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("certification", this.repo.getOne(id));
        return "certifications/edit";
    }

    @PostMapping("edit/{id}")
    public String editCertification(@Valid Certification certification, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "certifications/edit";
        } else {
            if (certification != null) {
               // Optional<Certification> certification1 = this.repo.findByCertificName(certification.getCertificName());

                Certification certification1 = this.repo.findByCertificName(certification.getCertificName());
                if (certification1 != null) {
                    model.addAttribute("existMSG", "Certification is exist");
                    return "certifications/edit";
                } else {
                    certification.setCertificName(certification.getCertificName());

                    this.repo.save(certification);
                    model.addAttribute("certification", new Certification());
                    model.addAttribute("successMsg", "Edit Success ");
                }

            }
        }

        return "redirect:/certification/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/certification/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        model.addAttribute("list", this.repo.findAll());
        return "certifications/list";
    }


}
