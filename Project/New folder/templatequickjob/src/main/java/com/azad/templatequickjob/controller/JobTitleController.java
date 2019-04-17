package com.azad.templatequickjob.controller;

import com.azad.templatequickjob.entity.JobTitle;
import com.azad.templatequickjob.entity.Role;
import com.azad.templatequickjob.repo.JobTitleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping (value = "/jobtitle/")
public class JobTitleController {

    @Autowired
    private JobTitleRepo repo;

    @GetMapping(value = "add")
    public String viewAdd(Model model){
        model.addAttribute("jobTitle",new JobTitle());
        return "jobtitles/add";
    }
    @PostMapping(value = "add")
    public String add(@Valid JobTitle jobTitle, BindingResult result, Model model){
        if(result.hasErrors()){
            return "jobtitles/add";
        }
        if(repo.existsJobTitleByJobTitleName(jobTitle.getJobTitleName())){
            model.addAttribute("rejectMsg","Already Have This Entry");
        }else{
            jobTitle.setJobTitleName(jobTitle.getJobTitleName().toUpperCase());
            this.repo.save(jobTitle);
            model.addAttribute("jobTitle",new JobTitle());
            model.addAttribute("successMsg","Successfully Saved!");
        }

        return "jobtitles/add";
    }
    @GetMapping(value = "edit/{id}")
    public String viewEdit(Model model, @PathVariable("id") Long id){
        model.addAttribute("jobTitle",repo.getOne(id));
        return "jobtitles/edit";
    }
    @PostMapping(value = "edit/{id}")
    public String edit(@Valid JobTitle jobTitle, BindingResult result, Model model,@PathVariable("id") Long id){
        if(result.hasErrors()){
            return "jobtitles/edit";
        }
        else {
            if (jobTitle != null) {
                JobTitle title = this.repo.findByJobTitleName(jobTitle.getJobTitleName());
                if (title != null) {
                    model.addAttribute("existMSG", "Role is exist");
                    return "jobtitles/edit";
                } else {
                    jobTitle.setJobTitleName(jobTitle.getJobTitleName().toUpperCase());

                    this.repo.save(jobTitle);
                    model.addAttribute("role", new JobTitle());
                    model.addAttribute("successMsg", "Edit Success ");
                }

            }
        }

//        Optional<JobTitle> title = this.repo.findByJobTitleName(jobTitle.getJobTitleName());
//
//        JobTitle title = this.repo.findByJobTitleName(jobTitle.getJobTitleName());
//        if(title.get().getId() != id){
//            model.addAttribute("rejectMsg","Already Have This Entry");
//            return "jobtitles/edit";
//        }else{
//            jobTitle.setId(id);
//            jobTitle.setJobTitleName(jobTitle.getJobTitleName().toUpperCase());
//            this.repo.save(jobTitle);
//        }

        return "redirect:/jobtitle/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id){
        if(id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/jobtitle/list";
    }

    @GetMapping(value = "list")
    public String list(Model model){
        model.addAttribute("list",this.repo.findAll());
        return "jobtitles/list";
    }

}
