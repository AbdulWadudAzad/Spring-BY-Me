package com.azad.templatequickjob.controller;


import com.azad.templatequickjob.entity.NewsAboutUs;
import com.azad.templatequickjob.repo.NewsAboutUsRepo;
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
@RequestMapping(value = "/hearus/")
public class NewsAboutUsController {

    @Autowired
    private NewsAboutUsRepo repo;

    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("newsAboutUs", new NewsAboutUs());
        return "hearaboutus/add";
    }

    @PostMapping(value = "add")
    public String add(@Valid NewsAboutUs newsAboutUs, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "hearaboutus/add";
        }
        if (repo.existsNewsAboutUsByHearAboutUs(newsAboutUs.getHearAboutUs())) {
            model.addAttribute("rejectMsg", "Already Have This Entry");
        } else {
            newsAboutUs.setHearAboutUs(newsAboutUs.getHearAboutUs());
            this.repo.save(newsAboutUs);
            model.addAttribute("newsAboutUs",new NewsAboutUs());
            model.addAttribute("successMsg", "Successfully Saved!");
        }

        return "hearaboutus/add";
    }

    @GetMapping("edit/{id}")
    public String editnewsAboutUsView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("newsAboutUs", this.repo.getOne(id));
        return "hearaboutus/edit";
    }

    @PostMapping("edit/{id}")
    public String edinewsAboutUs(@Valid NewsAboutUs newsAboutUs, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "hearaboutus/edit";
        } else {
            if (newsAboutUs != null) {
               // Optional<NewsAboutUs> newsAboutUs1 = this.repo.findByHearAboutUs(newsAboutUs.getHearAboutUs());
                NewsAboutUs newsAboutUs1 = this.repo.findByHearAboutUs(newsAboutUs.getHearAboutUs());
                if (newsAboutUs1 != null) {
                    model.addAttribute("existMSG", " Way of hear about us is exist");
                    return "hearaboutus/edit";
                } else {
                    newsAboutUs.setHearAboutUs(newsAboutUs.getHearAboutUs());

                    this.repo.save(newsAboutUs);
                    model.addAttribute("newsAboutUs", new NewsAboutUs());
                    model.addAttribute("successMsg", "Edit Success ");
                }

            }
        }

        return "redirect:/hearus/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/hearus/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        model.addAttribute("list", this.repo.findAll());
        return "hearaboutus/list";
    }


}
