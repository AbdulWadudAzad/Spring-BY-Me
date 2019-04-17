package com.azad.templatequickjob.controller;

import com.azad.templatequickjob.entity.JobFunction;
import com.azad.templatequickjob.repo.JobFunctionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/jobfunction/")
public class JobFunctionController {

    @Autowired
    private JobFunctionRepo repo;

    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("jobFunction", new JobFunction());
        return "jobfunctions/add";
    }

    @PostMapping(value = "add")
    public String add(@Valid JobFunction jobFunction, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "jobfunctions/add";
        }
        if (repo.existsJobFunctionByFunctionName(jobFunction.getFunctionName())) {
            model.addAttribute("rejectMsg", "Already Have This Entry");
        } else {
            jobFunction.setFunctionName(jobFunction.getFunctionName());
            this.repo.save(jobFunction);
            model.addAttribute("jobFunction",new JobFunction());
            model.addAttribute("successMsg", "Successfully Saved!");
        }

        return "jobfunctions/add";
    }

    @GetMapping("edit/{id}")
    public String editJobFunctionView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("jobFunction", this.repo.getOne(id));
        return "jobfunctions/edit";
    }

    @PostMapping("edit/{id}")
    public String editJobFunction(@Valid JobFunction jobFunction, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "jobfunctions/edit";
        } else {
            if (jobFunction != null) {
               // Optional<JobFunction> jobFunction1 = this.repo.findByFunctionName(jobFunction.getFunctionName());

                JobFunction jobFunction1 = this.repo.findByFunctionName(jobFunction.getFunctionName());
                if (jobFunction1 != null) {
                    model.addAttribute("existMSG", "Job Function is exist");
                    return "jobfunctions/edit";
                } else {
                    jobFunction.setFunctionName(jobFunction.getFunctionName());

                    this.repo.save(jobFunction);
                    model.addAttribute("jobFunction", new JobFunction());
                    model.addAttribute("successMsg", "Edit Success ");
                }

            }
        }

        return "redirect:/jobfunction/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/jobfunction/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        model.addAttribute("list", this.repo.findAll());
        return "jobfunctions/list";
    }


}
