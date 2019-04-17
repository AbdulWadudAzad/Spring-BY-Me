package com.azad.templatequickjob.controller;


import com.azad.templatequickjob.entity.Address;
import com.azad.templatequickjob.entity.EduLevel;
import com.azad.templatequickjob.entity.Exam;
import com.azad.templatequickjob.entity.User;
import com.azad.templatequickjob.repo.*;
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
import java.util.Collection;

@Controller
@RequestMapping(value = "/exam/")
public class ExamController {

    @Autowired
    private ExamRepo repo;
    @Autowired
    private EduLevelRepo eduLevelRepo;

    @Autowired
    private EduSubjectRepo eduSubjectRepo;

    @Autowired
    private ResultRepo resultRepo;

    @Autowired
    private BoardRepo boardRepo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("exam",new Exam());
        model.addAttribute("eduLevellist", this.eduLevelRepo.findAll());
        model.addAttribute("eduSubjectlist", this.eduSubjectRepo.findAll());
        model.addAttribute("resultlist", this.resultRepo.findAll());
        model.addAttribute("boardlist", this.boardRepo.findAll());
        model.addAttribute("userlist", this.userRepo.findAll());

        return "exams/add";
    }



    @PostMapping(value = "add")
    public String add(@Valid Exam exam, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "exams/add";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findByUserName(authentication.getName());
        exam.setUser(user);

        this.repo.save(exam);
        model.addAttribute("eduLevellist", this.eduLevelRepo.findAll());
        model.addAttribute("eduSubjectlist", this.eduSubjectRepo.findAll());
        model.addAttribute("resultlist", this.resultRepo.findAll());
        model.addAttribute("boardlist", this.boardRepo.findAll());
        model.addAttribute("userlist", this.userRepo.findAll());

        model.addAttribute("exam", new Exam());
        model.addAttribute("successMsg", "Successfully Saved!");


        return "exams/add";
    }

    @GetMapping("edit/{id}")
    public String editexamView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("exam", this.repo.getOne(id));
        model.addAttribute("eduLevelRepo", this.eduLevelRepo.findAll());
        model.addAttribute("eduSubjectlist", this.eduSubjectRepo.findAll());
        model.addAttribute("resultlist", this.resultRepo.findAll());
        model.addAttribute("boardlist", this.boardRepo.findAll());
        model.addAttribute("userlist", this.userRepo.findAll());

        return "exams/edit";
    }

    @PostMapping("edit/{id}")
    public String editexam(@Valid Exam exam, BindingResult bindingResult, Model model, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("eduLevellist", this.eduLevelRepo.findAll());
            model.addAttribute("eduSubjectlist", this.eduSubjectRepo.findAll());
            model.addAttribute("resultlist", this.resultRepo.findAll());
            model.addAttribute("boardlist", this.boardRepo.findAll());
            model.addAttribute("userlist", this.userRepo.findAll());

            return "exams/edit";
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepo.findByUserName(authentication.getName());
            exam.setUser(user);
            this.repo.save(exam);
            model.addAttribute("exam", new Exam());
            model.addAttribute("successMsg", "Edit Success ");

        }
        model.addAttribute("eduLevellist", this.eduLevelRepo.findAll());
        model.addAttribute("eduSubjectlist", this.eduSubjectRepo.findAll());
        model.addAttribute("resultlist", this.resultRepo.findAll());
        model.addAttribute("boardlist", this.boardRepo.findAll());
        model.addAttribute("userlist", this.userRepo.findAll());

        return "redirect:/exam/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/exam/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        User user=userRepo.findByUserName(auth.getName());
        model.addAttribute("list", this.repo.findAllByUser(user));
        return "exams/list";
    }


}
