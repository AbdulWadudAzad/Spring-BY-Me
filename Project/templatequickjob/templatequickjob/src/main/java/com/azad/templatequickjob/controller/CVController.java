package com.azad.templatequickjob.controller;

import com.azad.templatequickjob.entity.User;
import com.azad.templatequickjob.imagoeptimizer.ImageOptimizer;
import com.azad.templatequickjob.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping(value = "/cv/")
public class CVController {
    @Autowired
    private UserRepo repo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private CertificationRepo certificationRepo;

    @Autowired
    private ExamRepo examRepo;

    @Autowired
    private LanguageRepo languageRepo;

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private ReferanceRepo referanceRepo;

    @Autowired
    private ToolRepo toolRepo;

    @Autowired
    private WorkExperienceRepo workExperienceRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private ImageOptimizer imageOptimizer;

    private static String UPLOADED_FOLDER = "src/main/resources/static/images/";





    @PostMapping(value = "/update-user/{id}")
    public String editUser(@Valid User user, BindingResult bindingResult, @PathVariable("id") Long id, Model model, @RequestParam("file") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        try {
            //////////////////////For Image Upload start /////////////////////
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());

            Files.write(path, bytes);
            user.setFileName("new-" + file.getOriginalFilename());
            user.setFileSize(file.getSize());
            user.setFilePath("images/" + "new-" + file.getOriginalFilename());
            user.setFileExtension(file.getContentType());
            //////////////////////For Image Upload end/////////////////////
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user2 = this.repo.findByUserName(auth.getName());
            user.setRoles(user2.getRoles());
            this.repo.save(user);
            model.addAttribute("user", new User());
            model.addAttribute("rolelist", this.roleRepo.findAll());
            imageOptimizer.optimizeImage(UPLOADED_FOLDER, file, 1.0f, 100, 100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("rolelist", this.roleRepo.findAll());
        return "edit";
    }
    @GetMapping(value = "edit")
    public String profileView(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username",auth.getName());
        User user=this.repo.findByUserName(auth.getName());
        model.addAttribute("name", user.getFirstName());
        model.addAttribute("user", user);

        model.addAttribute("rolelist", this.roleRepo.findAll());
        return "/cv/edit";

    }

    @GetMapping(value = "views")
    public String displayCv(){
        return "/cv/view";
    }
}
