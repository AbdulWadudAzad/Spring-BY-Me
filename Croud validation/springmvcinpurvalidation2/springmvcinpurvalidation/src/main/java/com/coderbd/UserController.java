package com.coderbd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class UserController {
    @Autowired
    private UserRepo repo;

    @GetMapping(value = "/")
    public String index(Model model){
        model.addAttribute("list",this.repo.findAll());
        this.repo.findAll().forEach((c)->{
            System.out.println(c.toString());
        });
        return "index";
    }

    @GetMapping("/add")
    public String showForm(User user) {
        return "add-page";
    }

    @PostMapping("/add")
    public String save(@Valid User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "add-page";
        }
        user.setRegiDate(new Date());
        this.repo.save(user);
        model.addAttribute("user", new User());
        model.addAttribute("successMsg", "Congratulations! You are old enough to sign up for this site.");
        return "add-page";
    }


    @GetMapping("/edit/{id}")
    public String editView(Model model,@PathVariable("id") Long id) {
        model.addAttribute("user",this.repo.getOne(id));
        return "edit-page";
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid User user, BindingResult bindingResult,
                     @PathVariable("id") Long id) {

        if (bindingResult.hasErrors()) {
            return "edit-page";
        }
        this.repo.save(user);
        return "redirect:/";
    }


    @GetMapping(value = "/del/{id}")
    public String delete(@PathVariable("id") Long id){
        if(id != null){
            this.repo.deleteById(id);
        }
        return "redirect:/";
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
