package com.azad.demo;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/student/")
public class StudentController {

    @Autowired
    private StudentRepo repo;

    @GetMapping(value = "add")
    public String viewadd(Model model) {
        model.addAttribute("student", new Student());
        return "add";
    }

    @PostMapping(value = "add")
    public String add(@Valid Student student, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "add";
        } else {
            student.setName(student.getName());
            this.repo.save(student);
            model.addAttribute("student", new Student());
        }
        return "add";
    }

    @GetMapping(value = "edit/{id}")
    public String viewedit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("student", this.repo.getOne(id));
        return "edit";
    }

    @PostMapping(value = "edit/{id}")
    public String edit(@Valid Student student, BindingResult bindingResult, Model model, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "edit";
        } else {
            if (student != null) {
                Student country1 = this.repo.findByEmail(student.getEmail());
                if (country1 != null) {
                    model.addAttribute("existMSG", "Country is exist");
                    return "edit";
                } else {
                    student.setEmail(student.getEmail());
                    this.repo.save(student);
                    model.addAttribute("student", new Student());
                }

            }

        }
        return "redirect:/student/list";
    }
   @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/student/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        model.addAttribute("list", this.repo.findAll());
        return "list";
    }
}
