package com.azad.croudtheamleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    private StudentRepo repo;

    @GetMapping
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("student", new Student());
        mv.setViewName("index");
        return mv;
    }

    public ModelAndView add(@Valid Student student){
        ModelAndView mv = new ModelAndView();
        if(student.getName() !=null){
            this.repo.save(student);
            System.out.println("Success");
            mv.addObject("successMsg","Successfully Submit");
        }
        mv.setViewName("index");
        return mv;
    }

}
