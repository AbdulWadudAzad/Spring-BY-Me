package com.arefin.sunshinefarm.controller;

import com.arefin.sunshinefarm.entity.Role;
import com.arefin.sunshinefarm.entity.User;
import com.arefin.sunshinefarm.image.ImageOptimizer;
import com.arefin.sunshinefarm.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

@Controller
public class CustomersController {
    @Autowired
    private CropsRepo cropsRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    private CropsSummaryRepo cropsSummaryRepo;

    @Autowired
    private EmployeesRepo employeesRepo;

    @Autowired
    private EquipmentRepo equipmentRepo;

    @Autowired
    private ExpenseRepo expenseRepo;

    @Autowired
    private InsecticidesRepo insecticidesRepo;

    @Autowired
    private PesticidesRepo pesticidesRepo;

    @Autowired
    private SalesRepo salesRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    //Save the uploaded file to this folder
    private static final String UPLOADED_FOLDER = "src/main/resources/static/images/";

    @Autowired
    private ImageOptimizer imageOptimizer;

    @GetMapping(value = "/crops-list")
    public String cropsList(Model model){
        model.addAttribute("crops", this.cropsRepo.findAll());
        return "customers/crops-list";
    }

    @GetMapping(value = "/crops-summary-list")
    public String cropsSummaryList(Model model){
        model.addAttribute("cropssummary", this.cropsSummaryRepo.findAll());
        return "customers/crops-summary-list";
    }

    @GetMapping(value = "/employees-list")
    public String employeesList(Model model){
        model.addAttribute("employees", this.employeesRepo.findAll());
        return "customers/employees-list";
    }

    @GetMapping(value = "/equipment-list")
    public String equipmentList(Model model){
        model.addAttribute("equipment", this.equipmentRepo.findAll());
        return "customers/equipment-list";
    }

    @GetMapping(value = "/expenses-list")
    public String expensesList(Model model){
        model.addAttribute("expenses", this.expenseRepo.findAll());
        return "customers/expenses-list";
    }

    @GetMapping(value = "/insecticides-list")
    public String insecticidesList(Model model){
        model.addAttribute("insecticides", this.insecticidesRepo.findAll());
        return "customers/insecticides-list";
    }

    @GetMapping(value = "/pesticides-list")
    public String pesticidesList(Model model){
        model.addAttribute("pesticides", this.pesticidesRepo.findAll());
        return "customers/pesticides-list";
    }

    @GetMapping(value = "/sales-list")
    public String salesList(Model model){
        model.addAttribute("sales", this.salesRepo.findAll());
        return "customers/sales-list";
    }

//    @GetMapping(value = "/user-list")
//    public String userList(Model model){
//        model.addAttribute("user", this.userRepo.findAll());
//        return "customers/user-list";
//    }

    @GetMapping(value = "/user-list")
    public ModelAndView getList() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userRepo.findByUserName(auth.getName());
        ModelAndView modelAndView = new ModelAndView();
//        if(user.getRoles().equals()){
        modelAndView.addObject("user", this.userRepo.findAll());
//        }else {
//            modelAndView.addObject("user", this.userRepo.findAllByUserName(user.getUserName()));
//        }
        modelAndView.setViewName("customers/user-list");
        return modelAndView;
    }

    @GetMapping(value = "/update-user/{id}")
    public String edituserView(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", this.userRepo.getOne(id));
        model.addAttribute("rolelist", this.roleRepo.findAll());
        return "customers/update-user";
    }

    @PostMapping(value = "/update-user/{id}")
    public String editUser(@Valid User user, BindingResult bindingResult, @PathVariable("id") Long id, Model model, @RequestParam("file") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            return "customers/update-user";
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
            User user2 = this.userRepo.findByUserName(auth.getName());
            user.setRoles(user2.getRoles());
            this.userRepo.save(user);
            model.addAttribute("user", new User());
            model.addAttribute("rolelist", this.roleRepo.findAll());
            imageOptimizer.optimizeImage(UPLOADED_FOLDER, file, 1.0f, 100, 100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("rolelist", this.roleRepo.findAll());
        return "redirect:/user-list";
    }
}
