package com.azad.templatequickjob.controller;

import com.azad.templatequickjob.entity.*;
import com.azad.templatequickjob.imagoeptimizer.ImageOptimizer;
import com.azad.templatequickjob.jasper.CVService;
import com.azad.templatequickjob.jasper.MediaTypeUtils;
import com.azad.templatequickjob.repo.*;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.*;

@Controller
@RequestMapping(value = "/user/")
public class UserController {
    private static String UPLOADED_FOLDER = "src/main/resources/static/images/";

    @Autowired
    private UserRepo repo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private LanguageRepo languageRepo;

    @Autowired
    private WorkExperienceRepo workExperienceRepo;

    @Autowired
    private CertificationRepo certificationRepo;

    @Autowired
    private ToolRepo toolRepo;

    @Autowired
    private ReferanceRepo referanceRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private ExamRepo examRepo;

    @Autowired
    private ImageOptimizer imageOptimizer;
    @Autowired
    private CVService cvService;

    @Autowired
    ServletContext context;

    @GetMapping(value = "add")
    public String viewAdd(User user, Model model) {
        model.addAttribute("rolelist", this.roleRepo.findAll());
        return "users/add";
    }


    @PostMapping(value = "add")
    public String add(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("rolelist", this.roleRepo.findAll());
            return "users/add";
        }
        if (repo.existsByEmail(user.getEmail())) {
            model.addAttribute("rejectMsg", "Already Have This Entry");
        } else {
            user.setRegiDate(new Date());
            //////////For email $ password start/////////////
            String username = user.getEmail().split("\\@")[0];
            user.setUserName(username);
            user.setEnabled(true);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setConfirmationToken(UUID.randomUUID().toString());
            //////////For email $ password end/////////////

            this.repo.save(user);

            // for project
            Project project=new Project();
            project.setUser(user);
            projectRepo.save(project);
            // for language
            Language language=new Language();
            language.setUser(user);
            languageRepo.save(language);
            // for workExperience
            WorkExperience workExperience=new WorkExperience();
            workExperience.setUser(user);
            workExperienceRepo.save(workExperience);
            // for certification
            Certification certification=new Certification();
            certification.setUser(user);
            certificationRepo.save(certification);
            // for tool
            Tool tool=new Tool();
            tool.setUser(user);
            toolRepo.save(tool);
            // for referance
            Referance referance=new Referance();
            referance.setUser(user);
            referanceRepo.save(referance);
            // for address
            Address address=new Address();
            address.setUser(user);
            addressRepo.save(address);
            // for project
            Exam exam=new Exam();
            exam.setUser(user);
            examRepo.save(exam);

            model.addAttribute("successMsg", "Congrase......");
            model.addAttribute("user", new User()); //it's use for clear form
            model.addAttribute("rolelist", this.roleRepo.findAll());
        }

        return "users/add";
    }


    @GetMapping(value = "edit/{id}")
    public String viewEdit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", repo.getOne(id));
        model.addAttribute("rolelist", this.roleRepo.findAll());
        return "users/edit";
    }







    @PostMapping(value = "edit/{id}")
    public String edit(@Valid User user,@PathVariable("id") Long id, BindingResult result, Model model,@RequestParam("file") MultipartFile file){
        if(result.hasErrors()){
            return "users/edit";
        }
        Optional<User> u = this.repo.findByEmail(user.getEmail());
        if(u.get().getId() != id){
            model.addAttribute("rejectMsg","Already Have This Entry");
            return "users/edit";
        }else{
            user.setId(id);
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());

                Files.write(path, bytes);
                user.setFileName("new-" + file.getOriginalFilename());
                user.setFileSize(file.getSize());
                user.setFilePath("images/" + "new-" + file.getOriginalFilename());
                user.setFileExtension(file.getContentType());


                this.repo.save(user);
                model.addAttribute("user", new User());
                model.addAttribute("rolelist", roleRepo.findAll());
                imageOptimizer.optimizeImage(UPLOADED_FOLDER, file, 0.3f, 100, 100);
            }catch (Exception e){
                e.printStackTrace();
            }
           /* user.setId(id);
            this.repo.save(user);*/
        }
        model.addAttribute("rolelist", roleRepo.findAll());

        return "redirect:/user/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/user/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        model.addAttribute("list", this.repo.findAll());
        return "users/list";
    }

//
/////////////////////////////JASPER/////////////////////////////////
//
//    @RequestMapping(value = "/userreport", method = RequestMethod.GET)
//    public void report(HttpServletResponse response) throws Exception {
//        response.setContentType("text/html");
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(cvService.report());
//        InputStream inputStream = this.getClass().getResourceAsStream("/report.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
//        HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
//        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//        exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
//        exporter.exportReport();
//    }
//
//
//    ////////////////pdf//////////////////////
//
//    //    @RequestMapping(value = "/pdf", method = RequestMethod.GET,
////            produces = MediaType.APPLICATION_PDF_VALUE)
//    public void reportPdf() throws Exception {
//        String source = "src\\main\\resources\\report.jrxml";
//        try {
//            JasperCompileManager.compileReportToFile(source);
//        } catch (JRException e) {
//            e.printStackTrace();
//        }
//        String sourceFileName = "src\\main\\resources\\report1.jasper";
//        String printFileName = null;
//        String destFileName = "src\\main\\resources\\report.pdf";
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(cvService.report());
//        Map parameters = new HashMap();
//        try {
//            printFileName = JasperFillManager.fillReportToFile(sourceFileName,
//                    parameters, dataSource);
//            if (printFileName != null) {
//                JasperExportManager.exportReportToPdfFile(printFileName,
//                        destFileName);
//            }
//        } catch (JRException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @RequestMapping("/userpdf")
//    public ResponseEntity<InputStreamResource> downloadFile1() throws IOException {
//        try {
//            reportPdf();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String fileName="src\\\\main\\\\resources\\\\report.pdf";
//        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.context, fileName);
//
//        File file = new File(fileName);
//        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
//        return ResponseEntity.ok()
//                // Content-Disposition
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
//                // Content-Type
//                .contentType(mediaType)
//                // Contet-Length
//                .contentLength(file.length()) //
//                .body(resource);
//    }
}


//need when no controller and html page
//    @GetMapping(value = "/user-save")
//    public String saveUser() {
//
//        Set<Role> roles=new HashSet<>();
//        roles.add(new Role("SUPERADMIN"));
//        roles.add(new Role("ADMIN"));
//      roles.forEach((role -> {
//          roleRepo.save(role);
//      }));
//
//        roles.add(new Role(1L));
//        roles.add(new Role(2L));
//        User user = new User("Wadud","Azad", "azad", passwordEncoder.encode("Azad123"), "azadmiu37@gmail.com", true,null, roles);
//        repo.save(user);
//      return "success";
//    }
//
//    @GetMapping(value = "/register")
//    public ModelAndView displayRegister(User user){
//        ModelAndView mv=new ModelAndView();
//        mv.addObject("user",user);
//        mv.setViewName("signup");
//        return mv;
//    }

