package com.azad.templatequickjob.controller;


import com.azad.templatequickjob.entity.Language;
import com.azad.templatequickjob.repo.LanguageRepo;
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
@RequestMapping(value = "/language/")
public class LanguageController {

    @Autowired
    private LanguageRepo repo;

    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("language", new Language());
        return "languages/add";
    }

    @PostMapping(value = "add")
    public String add(@Valid Language language, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "languages/add";
        }
        if (repo.existsLanguageByLanguageName(language.getLanguageName())) {
            model.addAttribute("rejectMsg", "Already Have This Entry");
        } else {
            language.setLanguageName(language.getLanguageName());
            this.repo.save(language);
            model.addAttribute("language",new Language());
            model.addAttribute("successMsg", "Successfully Saved!");
        }

        return "languages/add";
    }

    @GetMapping("edit/{id}")
    public String editLanguageView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("language", this.repo.getOne(id));
        return "languages/edit";
    }

    @PostMapping("edit/{id}")
    public String editLanguage(@Valid Language language, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "languages/edit";
        } else {
            if (language != null) {
               // Optional<Language> language1 = this.repo.findByLanguageName(language.getLanguageName());

                Language language1 = this.repo.findByLanguageName(language.getLanguageName());
                if (language1 != null) {
                    model.addAttribute("existMSG", "Language is exist");
                    return "languages/edit";
                } else {
                    language.setLanguageName(language.getLanguageName());

                    this.repo.save(language);
                    model.addAttribute("language", new Language());
                    model.addAttribute("successMsg", "Edit Success ");
                }

            }
        }

        return "redirect:/language/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/language/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        model.addAttribute("list", this.repo.findAll());
        return "languages/list";
    }


}
