package com.azad.templatequickjob.controller;



import com.azad.templatequickjob.entity.Project;
import com.azad.templatequickjob.entity.User;
import com.azad.templatequickjob.repo.ProjectRepo;
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
@RequestMapping(value = "/project/")
public class ProjectController {

    @Autowired
    private ProjectRepo repo;
    @Autowired
    private UserRepo userRepo;

    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("project", new Project());
        model.addAttribute("userlist", this.userRepo.findAll());

        return "projects/add";
    }

    @PostMapping(value = "add")
    public String add(@Valid Project project, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "projects/add";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findByUserName(authentication.getName());
        project.setUser(user);
            this.repo.save(project);
            model.addAttribute("userlist", this.userRepo.findAll());

            model.addAttribute("project",new Project());
            model.addAttribute("successMsg", "Successfully Saved!");


        return "projects/add";
    }

    @GetMapping("edit/{id}")
    public String editProjectView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("project", this.repo.getOne(id));
        model.addAttribute("userlist", this.userRepo.findAll());

        return "projects/edit";
    }

    @PostMapping("edit/{id}")
    public String editProject(@Valid Project project, BindingResult bindingResult, Model model,@PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "projects/edit";
        }
             else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepo.findByUserName(authentication.getName());
            project.setUser(user);
                    this.repo.save(project);
            model.addAttribute("project", new Project());
            model.addAttribute("successMsg", "Edit Success ");

        }
        model.addAttribute("userlist", this.userRepo.findAll());

        return "redirect:/project/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/project/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        User user=userRepo.findByUserName(auth.getName());
        model.addAttribute("list", this.repo.findAllByUser(user));
        return "projects/list";
    }


}
