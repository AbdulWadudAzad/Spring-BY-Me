package com.azad.croudsqlthymleaf.controller;


import com.azad.croudsqlthymleaf.ImageOptimizer;
import com.azad.croudsqlthymleaf.repository.RoleRepo;
import com.azad.croudsqlthymleaf.repository.UserRepo;
import com.azad.croudsqlthymleaf.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Controller
public class HomeController {
    private static String UPLOADED_FOLDER = "src/main/resources/static/image/";
    @Autowired
    private UserRepo repo;
    @Autowired
    private ImageOptimizer imageOptimizer;

    @Autowired
    private RoleRepo roleRepo;

    @GetMapping(value = "/add")
    public String showForm(User user, Model model) {
        model.addAttribute("roleList", this.roleRepo.findAll());
        return "add-page";
    }


    @PostMapping(value = "/add")
    public String save(@Valid User user, BindingResult bindingResult, Model model, @RequestParam("file") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            return "add-page";
        } else {
            user.setRegiDate(new Date());
            try {
                /////////For Image Upload start ////////
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                Files.write(path, bytes);
                user.setFileName("new-" + file.getOriginalFilename());
                user.setFileSize(file.getSize());
                user.setFilePath("image/" + "new-" + file.getOriginalFilename());
                user.setFileExtension(file.getContentType());
                //////////For Image Upload end/////////////
                this.repo.save(user);
                model.addAttribute("user", new User()); //it's use for clear form

                model.addAttribute("roleList", this.roleRepo.findAll());

                model.addAttribute("successMsg", "Congrase......");
                imageOptimizer.optimizeImage(UPLOADED_FOLDER, file, 0.3f, 100, 100);
            } catch (Exception e) {
                e.printStackTrace();
            }

            model.addAttribute(this.roleRepo.findAll());

            return "add-page";
        }
    }

    @GetMapping("/edit/{id}")
    public String editView(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", this.repo.getOne(id));
        return "edit-page";
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid User user, BindingResult bindingResult,
                       Model model, @PathVariable("id") Long id) {

        if (bindingResult.hasErrors()) {
            return "edit-page";
        } else {
            this.repo.save(user);
            model.addAttribute("user", new User());
        }
        return "redirect:/";
    }

    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("list", this.repo.findAll());
        this.repo.findAll().forEach((c) -> {      //Here "->" is use for declare an Array
            System.out.println(c.toString());
        });
        return "index";
    }

    @GetMapping(value = "/del/{id}")
    public String delete(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/";
    }

}


