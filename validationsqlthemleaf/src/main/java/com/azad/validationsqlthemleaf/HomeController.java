package com.azad.validationsqlthemleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    private UserRepo repo;

    @GetMapping(value = "/add")

    public String showForm(User user) {
        return "add-page";
    }


    @PostMapping(value = "/add")
    public String save(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "add-page";
        }
        this.repo.save(user);
        model.addAttribute("user", new User()); //it's use for clear form
        model.addAttribute("successMsg", "Congrase......");
        return "add-page";
    }
    @GetMapping("/edit/{id}")
    public String editView(Model model,@PathVariable("id") Long id) {
        model.addAttribute("user",this.repo.getOne(id));
        return "edit-page";
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid User user, BindingResult bindingResult,
                       Model model, @PathVariable("id") Long id) {

        if (bindingResult.hasErrors()) {
            return "edit-page";
        }
        this.repo.save(user);
        model.addAttribute("user", new User());
        return "redirect:/";
    }

    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("list", this.repo.findAll());
        this.repo.findAll().forEach((c)->{
            System.out.println(c.toString());
        });
        return "index";
    }

    @GetMapping(value = "/del/{id}")
    public String delete(@PathVariable("id") Long id){
        if(id !=null){
            this.repo.deleteById(id);
        }
        return "redirect:/";
    }

}
