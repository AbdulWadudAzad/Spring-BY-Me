package com.coderbd.springdifferentlayers.controller;

import com.coderbd.springdifferentlayers.dto.StudentDTO;
import com.coderbd.springdifferentlayers.dto.StudentDTOReport;
import com.coderbd.springdifferentlayers.entity.Student;
import com.coderbd.springdifferentlayers.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/test/{id}")
    public Student getStudent(@PathVariable("id") Long id){
        return studentService.findById(id);
    }


    @GetMapping(value = "/list")
    public List<StudentDTO> getStudents(){
        return studentService.getAll();
    }

    @GetMapping(value = "/report")
    public  List<StudentDTOReport> getReport(){
        Map<String,Long> maps=new HashMap<>();
        for(StudentDTO studentDTO : studentService.getAll()){
            maps.put(studentDTO.getEmail(),studentService.countNoOfStudents(studentDTO.getEmail()));

        }

        List<StudentDTOReport> list=new ArrayList<>();

        for(Map.Entry<String,Long> s : maps.entrySet() ){
            list.add(new StudentDTOReport(s.getValue(),s.getKey()));
        }



        return list;
    }

    @GetMapping(value = "/report2")
    public   Map<String,Long> getReport2(){
        Map<String,Long> maps=new HashMap<>();
        for(StudentDTO studentDTO : studentService.getAll()){
            maps.put(studentDTO.getEmail(),studentService.countNoOfStudents(studentDTO.getEmail()));

        }
        return maps;
    }
}
