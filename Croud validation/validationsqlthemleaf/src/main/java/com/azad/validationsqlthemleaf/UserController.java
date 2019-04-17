package com.azad.validationsqlthemleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserRepo repo;

@GetMapping(value = "/")
    public String showForm(User user){
        return "form";
    }


    @GetMapping(value = "/")
    public String save(@Valid User user, BindingResult bindingResult, Model model){
    if(bindingResult.hasErrors()){
        return "form";
    }
    this.repo.save(user);
    model.addAttribute("user", new User()); //it's use for clear form
    model.addAttribute("successMsg","Congrase......");
    return "form";
    }

    @GetMapping(value = "/result")
    public String showResult(){
    return "result";
    }


}
