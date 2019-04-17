package com.azad.templatequickjob.controller;


import com.azad.templatequickjob.entity.Language;
import com.azad.templatequickjob.entity.User;
import com.azad.templatequickjob.repo.LanguageRepo;
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
@RequestMapping(value = "/language/")
public class LanguageController {

    @Autowired
    private LanguageRepo repo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("language", new Language());
        model.addAttribute("userlist", this.userRepo.findAll());
        return "languages/add";
    }

    @PostMapping(value = "add")
    public String add(@Valid Language language, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "languages/add";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findByUserName(authentication.getName());
        language.setUser(user);
        this.repo.save(language);
        model.addAttribute("userlist", this.userRepo.findAll());
        model.addAttribute("language", new Language());
        model.addAttribute("successMsg", "Successfully Saved!");


        return"languages/add";
}

    @GetMapping("edit/{id}")
    public String editLanguageView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("language", this.repo.getOne(id));
        model.addAttribute("userlist", this.userRepo.findAll());

        return "languages/edit";
    }

    @PostMapping("edit/{id}")
    public String editLanguage(@Valid Language language, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userlist", this.userRepo.findAll());
            return "languages/edit";
        } else {
            if (language != null) {
                // Optional<Language> language1 = this.repo.findByLanguageName(language.getLanguageName());

                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                User user = userRepo.findByUserName(authentication.getName());
                language.setUser(user);

                this.repo.save(language);
                model.addAttribute("language", new Language());
                model.addAttribute("successMsg", "Edit Success ");
            }


    }
        model.addAttribute("userlist",this.userRepo.findAll());
                return"redirect:/language/list";
                }

@GetMapping(value = "del/{id}")
public String del(@PathVariable("id") Long id){
        if(id!=null){
        this.repo.deleteById(id);
        }
        return"redirect:/language/list";
        }

@GetMapping(value = "list")
public String list(Model model){
        Authentication auth=SecurityContextHolder.getContext().getAuthentication();
        User user=userRepo.findByUserName(auth.getName());
        model.addAttribute("list",this.repo.findAllByUser(user));
        return"languages/list";
        }


        }
