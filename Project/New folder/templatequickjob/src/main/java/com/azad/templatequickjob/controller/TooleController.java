package com.azad.templatequickjob.controller;


import com.azad.templatequickjob.entity.Tool;
import com.azad.templatequickjob.repo.ToolRepo;
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
@RequestMapping(value = "/tool/")
public class TooleController {

    @Autowired
    private ToolRepo repo;

    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("tool", new Tool());
        return "tools/add";
    }

    @PostMapping(value = "add")
    public String add(@Valid Tool tool, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "tools/add";
        }
        if (repo.existsTooleByToolName(tool.getToolName())) {
            model.addAttribute("rejectMsg", "Already Have This Entry");
        } else {
            tool.setToolName(tool.getToolName());
            this.repo.save(tool);
            model.addAttribute("tool",new Tool());
            model.addAttribute("successMsg", "Successfully Saved!");
        }

        return "tools/add";
    }

    @GetMapping("edit/{id}")
    public String editToolView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("tool", this.repo.getOne(id));
        return "tools/edit";
    }

    @PostMapping("edit/{id}")
    public String editTool(@Valid Tool tool, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "tools/edit";
        } else {
            if (tool != null) {
               // Optional<Tool> tool1 = this.repo.findByToolName(tool.getToolName());

                Tool tool1 = this.repo.findByToolName(tool.getToolName());
                if (tool1 != null) {
                    model.addAttribute("existMSG", "Tool is exist");
                    return "tools/edit";
                } else {
                    tool.setToolName(tool.getToolName());

                    this.repo.save(tool);
                    model.addAttribute("tool", new Tool());
                    model.addAttribute("successMsg", "Edit Success ");
                }

            }
        }

        return "redirect:/tool/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/tool/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        model.addAttribute("list", this.repo.findAll());
        return "tools/list";
    }


}
