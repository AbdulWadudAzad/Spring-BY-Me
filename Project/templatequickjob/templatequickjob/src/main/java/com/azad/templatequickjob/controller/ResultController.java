package com.azad.templatequickjob.controller;



import com.azad.templatequickjob.entity.Result;
import com.azad.templatequickjob.repo.ResultRepo;
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
@RequestMapping(value = "/result/")
public class ResultController {

    @Autowired
    private ResultRepo repo;

    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("result", new Result());
        return "results/add";
    }

    @PostMapping(value = "add")
    public String add(@Valid Result result, BindingResult results, Model model) {
        if (results.hasErrors()) {
            return "results/add";
        }
        if (repo.existsResultByResultName(result.getResultName())) {
            model.addAttribute("rejectMsg", "Already Have This Entry");
        } else {
            result.setResultName(result.getResultName());
            this.repo.save(result);
            model.addAttribute("result",new Result());
            model.addAttribute("successMsg", "Successfully Saved!");
        }

        return "results/add";
    }

    @GetMapping("edit/{id}")
    public String editResultView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("result", this.repo.getOne(id));
        return "results/edit";
    }

    @PostMapping("edit/{id}")
    public String editResult(@Valid Result result, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "results/edit";
        } else {
            if (result != null) {

                Result result1 = this.repo.findByResultName(result.getResultName());
                if (result1 != null) {
                    model.addAttribute("existMSG", "Result is exist");
                    return "results/edit";
                } else {
                    result.setResultName(result.getResultName());

                    this.repo.save(result);
                    model.addAttribute("result", new Result());
                    model.addAttribute("successMsg", "Edit Success ");
                }

            }
        }

        return "redirect:/result/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/result/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        model.addAttribute("list", this.repo.findAll());
        return "results/list";
    }


}
