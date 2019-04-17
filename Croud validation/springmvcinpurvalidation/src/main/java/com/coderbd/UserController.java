package com.coderbd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserRepo repo;

    @GetMapping("/")
    public String showForm(User user) {
        return "form";
    }

    @PostMapping("/")
    public String save(@Valid User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "form";
        }
        this.repo.save(user);
        model.addAttribute("user", new User());
        model.addAttribute("successMsg", "Congratulations! You are old enough to sign up for this site.");
        return "form";
    }

    @GetMapping("/results")
    public String showResults() {
        return "results";
    }

//
//    @PostMapping("/")
//    public String save(@Valid User user, BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            return "form";
//        }
//        this.repo.save(user);
//
//        return "redirect:/results";
//    }
}
