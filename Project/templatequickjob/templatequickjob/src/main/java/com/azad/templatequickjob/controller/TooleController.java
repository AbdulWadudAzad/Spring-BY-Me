package com.azad.templatequickjob.controller;


import com.azad.templatequickjob.entity.Tool;
import com.azad.templatequickjob.entity.User;
import com.azad.templatequickjob.repo.ToolRepo;
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
@RequestMapping(value = "/tool/")
public class TooleController {

    @Autowired
    private ToolRepo repo;
    @Autowired
    private UserRepo userRepo;

    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("tool", new Tool());
        model.addAttribute("userlist", this.userRepo.findAll());

        return "tools/add";
    }

    @PostMapping(value = "add")
    public String add(@Valid Tool tool, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "tools/add";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findByUserName(authentication.getName());
        tool.setUser(user);

        this.repo.save(tool);
        model.addAttribute("userlist", this.userRepo.findAll());

        model.addAttribute("tool", new Tool());
        model.addAttribute("successMsg", "Successfully Saved!");


        return "tools/add";
    }

    @GetMapping("edit/{id}")
    public String editToolView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("tool", this.repo.getOne(id));
        model.addAttribute("userlist", this.userRepo.findAll());

        return "tools/edit";
    }

    @PostMapping("edit/{id}")
    public String editTool(@Valid Tool tool, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userlist", this.userRepo.findAll());

            return "tools/edit";
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepo.findByUserName(authentication.getName());
            tool.setUser(user);

            this.repo.save(tool);
            model.addAttribute("tool", new Tool());
            model.addAttribute("successMsg", "Edit Success ");
        }


        model.addAttribute("userlist", this.userRepo.findAll());

        return "redirect:/tool/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/tool/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findByUserName(auth.getName());
        model.addAttribute("list", this.repo.findAllByUser(user));
        return "tools/list";
    }


}
