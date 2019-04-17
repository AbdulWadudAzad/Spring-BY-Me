package com.azad.templatequickjob.controller;


import com.azad.templatequickjob.entity.JobApply;
import com.azad.templatequickjob.entity.JobPost;
import com.azad.templatequickjob.entity.User;
import com.azad.templatequickjob.jasper.JobPostService;
import com.azad.templatequickjob.jasper.MediaTypeUtils;
import com.azad.templatequickjob.repo.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.repo.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Path;
import javax.validation.Valid;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Controller

public class JobPostController {

    @Autowired
    private JobPostRepo repo;
    @Autowired
    private CountryRepo countryRepo;
    @Autowired
    private DivisionRepo divisionRepo;
    @Autowired
    private DistricRepo districRepo;
    @Autowired
    private JobTitleRepo jobTitleRepo;
    @Autowired
    private EmploymentTypeRepo employmentTypeRepo;
    @Autowired
    private JobCategoryRepo jobCategoryRepo;
    @Autowired
    private SeniorityLevelRepo seniorityLevelRepo;
    @Autowired
    private CompanyIndustryRepo companyIndustryRepo;
    @Autowired
    private SkillRepo skillRepo;
    @Autowired
    private YearExperienceRepo yearExperienceRepo;
    @Autowired
    private LanguageRepo languageRepo;
    @Autowired
    private EduLevelRepo eduLevelRepo;
    @Autowired
    private CertificationRepo certificationRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JobApplyRepo jobApplyRepo;




    @Autowired
    private JobPostService jobPostService;
    @Autowired
    ServletContext context;

//    @GetMapping(value = "/profile")
//    public String profileView(Model model) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        model.addAttribute("username",auth.getName());
//      Address address=this.repo.findByCountry_CountryName(auth.getName());
//        model.addAttribute("name", user.getFirstName());
//        model.addAttribute("user", user);
//
//        return "userprofile/profile";
//
//    }


    @GetMapping(value = "/jobpost/add")
    public String viewAdd(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("companyname", auth.getName());
        JobPost jobPost = this.repo.findByCompanyName(auth.getName());


        model.addAttribute("jobPost", new JobPost());
        model.addAttribute("countrylist", this.countryRepo.findAll());
        model.addAttribute("divisionlist", this.divisionRepo.findAll());
        model.addAttribute("districlist", this.districRepo.findAll());
        model.addAttribute("jobTitlelist", this.jobTitleRepo.findAll());
        model.addAttribute("employmentTypelist", this.employmentTypeRepo.findAll());
        model.addAttribute("jobCategorylist", this.jobCategoryRepo.findAll());
        model.addAttribute("seniorityLevellist", this.seniorityLevelRepo.findAll());
        model.addAttribute("companyIndustrylist", this.companyIndustryRepo.findAll());
        model.addAttribute("skilllist", this.skillRepo.findAll());
        model.addAttribute("yearExperiencelist", this.yearExperienceRepo.findAll());
        model.addAttribute("languagelist", this.languageRepo.findAll());
        model.addAttribute("eduLevellist", this.eduLevelRepo.findAll());
        model.addAttribute("certificationlist", this.certificationRepo.findAll());
        model.addAttribute("userlist", this.userRepo.findAll());

        return "jobposts/add";
    }

    @PostMapping(value = "/jobpost/add")
    public String add(@Valid JobPost jobPost, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "jobposts/add";
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepo.findByUserName(authentication.getName());
            jobPost.setUser(user);

            this.repo.save(jobPost);
            model.addAttribute("countrylist", this.countryRepo.findAll());
            model.addAttribute("divisionlist", this.divisionRepo.findAll());
            model.addAttribute("districlist", this.districRepo.findAll());
            model.addAttribute("jobTitlelist", this.jobTitleRepo.findAll());
            model.addAttribute("employmentTypelist", this.employmentTypeRepo.findAll());
            model.addAttribute("jobCategorylist", this.jobCategoryRepo.findAll());
            model.addAttribute("seniorityLevellist", this.seniorityLevelRepo.findAll());
            model.addAttribute("companyIndustrylist", this.companyIndustryRepo.findAll());
            model.addAttribute("skilllist", this.skillRepo.findAll());
            model.addAttribute("yearExperiencelist", this.yearExperienceRepo.findAll());
            model.addAttribute("languagelist", this.languageRepo.findAll());
            model.addAttribute("eduLevellist", this.eduLevelRepo.findAll());
            model.addAttribute("certificationlist", this.certificationRepo.findAll());
            model.addAttribute("userlist", this.userRepo.findAll());

            model.addAttribute("jobPost", new JobPost());
            model.addAttribute("successMsg", "Successfully Saved!");
        }

        return "jobposts/add";
    }

    @GetMapping(value = "/jobpost/edit/{id}")
    public String viewEdit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("jobPost", repo.getOne(id));
        model.addAttribute("countrylist", this.countryRepo.findAll());
        model.addAttribute("divisionlist", this.divisionRepo.findAll());
        model.addAttribute("districlist", this.districRepo.findAll());
        model.addAttribute("jobTitlelist", this.jobTitleRepo.findAll());
        model.addAttribute("employmentTypelist", this.employmentTypeRepo.findAll());
        model.addAttribute("jobCategorylist", this.jobCategoryRepo.findAll());
        model.addAttribute("seniorityLevellist", this.seniorityLevelRepo.findAll());
        model.addAttribute("companyIndustrylist", this.companyIndustryRepo.findAll());
        model.addAttribute("skilllist", this.skillRepo.findAll());
        model.addAttribute("yearExperiencelist", this.yearExperienceRepo.findAll());
        model.addAttribute("languagelist", this.languageRepo.findAll());
        model.addAttribute("eduLevellist", this.eduLevelRepo.findAll());
        model.addAttribute("certificationlist", this.certificationRepo.findAll());
        model.addAttribute("userlist", this.userRepo.findAll());


        return "jobposts/edit";
    }

    @PostMapping("/jobpost/edit/{id}")
    public String editCity(@Valid JobPost jobPost, BindingResult bindingResult, Model model, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("countrylist", this.countryRepo.findAll());
            model.addAttribute("divisionlist", this.divisionRepo.findAll());
            model.addAttribute("districlist", this.districRepo.findAll());
            model.addAttribute("jobTitlelist", this.jobTitleRepo.findAll());
            model.addAttribute("employmentTypelist", this.employmentTypeRepo.findAll());
            model.addAttribute("jobCategorylist", this.jobCategoryRepo.findAll());
            model.addAttribute("seniorityLevellist", this.seniorityLevelRepo.findAll());
            model.addAttribute("companyIndustrylist", this.companyIndustryRepo.findAll());
            model.addAttribute("skilllist", this.skillRepo.findAll());
            model.addAttribute("yearExperiencelist", this.yearExperienceRepo.findAll());
            model.addAttribute("languagelist", this.languageRepo.findAll());
            model.addAttribute("eduLevellist", this.eduLevelRepo.findAll());
            model.addAttribute("certificationlist", this.certificationRepo.findAll());
            model.addAttribute("userlist", this.userRepo.findAll());


            return "jobposts/edit";
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepo.findByUserName(authentication.getName());
            jobPost.setUser(user);
            this.repo.save(jobPost);
            model.addAttribute("jobPost", new JobPost());
            model.addAttribute("successMsg", "Edit Success ");
        }

        model.addAttribute("countrylist", this.countryRepo.findAll());
        model.addAttribute("divisionlist", this.divisionRepo.findAll());
        model.addAttribute("districlist", this.districRepo.findAll());
        model.addAttribute("jobTitlelist", this.jobTitleRepo.findAll());
        model.addAttribute("employmentTypelist", this.employmentTypeRepo.findAll());
        model.addAttribute("jobCategorylist", this.jobCategoryRepo.findAll());
        model.addAttribute("seniorityLevellist", this.seniorityLevelRepo.findAll());
        model.addAttribute("companyIndustrylist", this.companyIndustryRepo.findAll());
        model.addAttribute("skilllist", this.skillRepo.findAll());
        model.addAttribute("yearExperiencelist", this.yearExperienceRepo.findAll());
        model.addAttribute("languagelist", this.languageRepo.findAll());
        model.addAttribute("eduLevellist", this.eduLevelRepo.findAll());
        model.addAttribute("certificationlist", this.certificationRepo.findAll());
        model.addAttribute("userlist", this.userRepo.findAll());


        return "redirect:/jobpost/list";
    }


    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/jobpost/list";
    }

    @GetMapping(value = "/jobpost/list")
    public String list(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findByUserName(auth.getName());
        model.addAttribute("list", this.repo.findAllByUser(user));
        return "jobposts/list";
    }

    @GetMapping(value = "/")
    public String publicview(Model model) {
        model.addAttribute("gridlist", this.repo.findAll());
        return "publicview";
    }


    @GetMapping(value = "/quickjob")
    public String listview(Model model) {
        model.addAttribute("gridlist", this.repo.findAll());
        return "index";
    }


    @GetMapping(value = "/jobview/{id}")
    public String jobview(Model model, @PathVariable("id") Long id) {
        model.addAttribute("viewlist", this.repo.getOne(id));
        return "jobview";
    }


    @GetMapping(value = "/jobapplication")
    public String jobapplicationview(Model model, @PathVariable("id") Long id) {
        model.addAttribute("applicationlist", this.jobApplyRepo.getOne(id));
        return "jobapplicationlist";
    }




    //////////////////////////apply job
    @GetMapping(value = "/jobviewform/{id}")
    public String jobListview(Model model, @PathVariable("id") Long id) {
        model.addAttribute("view", this.repo.getOne(id));
        return "jobviewform";
    }

    @PostMapping(value = "/jobviewform/{id}")
    public String addAppyJob(@Valid JobPost jobPost, BindingResult result, Model model, @PathVariable("id") Long id) {
        if (result.hasErrors()) {
            model.addAttribute("view", this.repo.getOne(id));
            return "jobviewform";
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepo.findByUserName(authentication.getName());
            jobPost.setUser(user);

            JobApply jobApply = new JobApply();
            jobApply.setLastModifiedDate(new Date());
            jobApply.setJobPostId(new JobPost(id));
            jobApply.setUserId(user);

            jobApplyRepo.save(jobApply);

            model.addAttribute("view", this.repo.getOne(id));
            model.addAttribute("successMsg", "Successfully Saved!");
        }

        return "jobviewform";
    }





















////////////////////////////JASPER/////////////////////////////////

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public void report(HttpServletResponse response) throws Exception {
        response.setContentType("text/html");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(jobPostService.report());
        InputStream inputStream = this.getClass().getResourceAsStream("/report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
        HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
        exporter.exportReport();
    }


    ////////////////pdf//////////////////////

    //    @RequestMapping(value = "/pdf", method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_PDF_VALUE)
    public void reportPdf() throws Exception {
        String source = "src\\main\\resources\\report.jrxml";
        try {
            JasperCompileManager.compileReportToFile(source);
        } catch (JRException e) {
            e.printStackTrace();
        }
        String sourceFileName = "src\\main\\resources\\report1.jasper";
        String printFileName = null;
        String destFileName = "src\\main\\resources\\report.pdf";
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(jobPostService.report());
        Map parameters = new HashMap();
        try {
            printFileName = JasperFillManager.fillReportToFile(sourceFileName,
                    parameters, dataSource);
            if (printFileName != null) {
                JasperExportManager.exportReportToPdfFile(printFileName,
                        destFileName);
            }
        } catch (JRException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/pdf")
    public ResponseEntity<InputStreamResource> downloadFile1() throws IOException {
        try {
            reportPdf();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileName="src\\\\main\\\\resources\\\\report.pdf";
        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.context, fileName);

        File file = new File(fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                // Content-Type
                .contentType(mediaType)
                // Contet-Length
                .contentLength(file.length()) //
                .body(resource);
    }
}

