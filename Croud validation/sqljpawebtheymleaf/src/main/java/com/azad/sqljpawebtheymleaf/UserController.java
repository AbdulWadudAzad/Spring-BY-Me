package com.azad.sqljpawebtheymleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserRepo repo;

    @GetMapping(value = "/add")
    @ResponseBody
    public String showForm(User user) {
        return "add-page";
    }

    @PostMapping("/add")
    @ResponseBody
    public String save(@Valid User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "add-page";
        }
        this.repo.save(user);
        model.addAttribute("user", new User());
        model.addAttribute("successMsg", "Congratulations! You are old enough to sign up for this site.");
        return "add-page";
    }
    @GetMapping(value = "/")
    @ResponseBody
    public String index(Model model){
        model.addAttribute("list",this.repo.findAll());
        return "index";
    }


}
