package com.azad.templatequickjob.controller;


import com.azad.templatequickjob.entity.Certification;
import com.azad.templatequickjob.entity.User;
import com.azad.templatequickjob.repo.CertificationRepo;
import com.azad.templatequickjob.repo.UserRepo;
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
@RequestMapping(value = "/certification/")
public class CertificationController {

    @Autowired
    private CertificationRepo repo;
    @Autowired
    private UserRepo userRepo;

    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("certification", new Certification());
        model.addAttribute("userlist", this.userRepo.findAll());

        return "certifications/add";
    }

    @PostMapping(value = "add")
    public String add(@Valid Certification certification, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "certifications/add";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findByUserName(authentication.getName());
        certification.setUser(user);

            this.repo.save(certification);
            model.addAttribute("userlist", this.userRepo.findAll());
            model.addAttribute("certification",new Certification());
            model.addAttribute("successMsg", "Successfully Saved!");


        return "certifications/add";
    }

    @GetMapping("edit/{id}")
    public String editCertificationView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("certification", this.repo.getOne(id));
        model.addAttribute("userlist", this.userRepo.findAll());

        return "certifications/edit";
    }

    @PostMapping("edit/{id}")
    public String editCertification(@Valid Certification certification, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userlist", this.userRepo.findAll());

            return "certifications/edit";
        } else {
            if (certification != null) {
               // Optional<Certification> certification1 = this.repo.findByCertificName(certification.getCertificName());

                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                User user = userRepo.findByUserName(authentication.getName());
                certification.setUser(user);

                    this.repo.save(certification);
                    model.addAttribute("certification", new Certification());
                    model.addAttribute("successMsg", "Edit Success ");
                }


        }
        model.addAttribute("userlist", this.userRepo.findAll());

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
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        User user=userRepo.findByUserName(auth.getName());
        model.addAttribute("list", this.repo.findAllByUser(user));
        return "certifications/list";
    }


}
