package com.azad.demo;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String addview(Model model) {
        model.addAttribute("student", new Student());
        return "students/add";
    }

    @PostMapping(value = "add")
    public String add(Model model, @Valid Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "students/add";
        } else {
            student.setName(student.getName());
            this.repo.save(student);
            model.addAttribute("student", new Student());
            model.addAttribute("successMsg", "Successfully saved");

        }
        return "students/add";
    }

    @GetMapping("edit/{id}")
    public String editstudentView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("student", this.repo.getOne(id));
        return "students/edit";
    }

    @PostMapping("edit/{id}")
    public String editstudent(@Valid Student student, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "students/edit";
        } else {
            if (student != null) {

                Student student1 = this.repo.findByEmail(student.getEmail());
                if (student1 != null) {
                    model.addAttribute("existMSG", "Student is exist");
                    return "students/edit";
                } else {
                    student.setEmail(student.getEmail());
                    this.repo.save(student);
                    model.addAttribute("student", new Student());
                    model.addAttribute("successMsg", "Edit Success ");
                }

            }
        }

        return "redirect:/student/list";
    }

    @GetMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/student/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        model.addAttribute("list", this.repo.findAll());
        return "students/list";
    }

}
