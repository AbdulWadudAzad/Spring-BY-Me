package com.azad.templatequickjob.controller;

import com.azad.templatequickjob.entity.JobCategory;
import com.azad.templatequickjob.entity.JobTitle;
import com.azad.templatequickjob.repo.JobCategoryRepo;
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

@Controller
@RequestMapping (value = "/jobcategory/")
public class JobCategoryController {

    @Autowired
    private JobCategoryRepo repo;

    @GetMapping(value = "add")
    public String viewAdd(Model model){
        model.addAttribute("jobCategory",new JobCategory());
        return "jobcategorys/add";
    }
    @PostMapping(value = "add")
    public String add(@Valid JobCategory jobCategory, BindingResult result, Model model){
        if(result.hasErrors()){
            return "jobcategorys/add";
        }
        if(repo.existsJobCategoryByJobCategoryName(jobCategory.getJobCategoryName())){
            model.addAttribute("rejectMsg","Already Have This Entry");
        }else{
            jobCategory.setJobCategoryName(jobCategory.getJobCategoryName());
            this.repo.save(jobCategory);
            model.addAttribute("jobCategory",new JobCategory());
            model.addAttribute("successMsg","Successfully Saved!");
        }

        return "jobcategorys/add";
    }
    @GetMapping(value = "edit/{id}")
    public String viewEdit(Model model, @PathVariable("id") Long id){
        model.addAttribute("jobCategory",repo.getOne(id));
        return "jobcategorys/edit";
    }
    @PostMapping(value = "edit/{id}")
    public String edit(@Valid JobCategory jobCategory, BindingResult result, Model model,@PathVariable("id") Long id){
        if(result.hasErrors()){
            return "jobcategorys/edit";
        }
        else {
            if (jobCategory != null) {
                JobCategory title = this.repo.findByJobCategoryName(jobCategory.getJobCategoryName());
                if (title != null) {
                    model.addAttribute("existMSG", "Role is exist");
                    return "jobcategorys/edit";
                } else {
                    jobCategory.setJobCategoryName(jobCategory.getJobCategoryName());

                    this.repo.save(jobCategory);
                    model.addAttribute("jobCategory", new JobCategory());
                    model.addAttribute("successMsg", "Edit Success ");
                }

            }
        }

        return "redirect:/jobcategory/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id){
        if(id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/jobcategory/list";
    }

    @GetMapping(value = "list")
    public String list(Model model){
        model.addAttribute("list",this.repo.findAll());
        return "jobcategorys/list";
    }

}
