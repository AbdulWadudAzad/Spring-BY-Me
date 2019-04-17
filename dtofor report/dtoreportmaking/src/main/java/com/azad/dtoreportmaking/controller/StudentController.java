package com.azad.dtoreportmaking.controller;

import com.azad.dtoreportmaking.dto.StudentDTO;
import com.azad.dtoreportmaking.dto.StudentDTOReport;
import com.azad.dtoreportmaking.entity.Student;
import com.azad.dtoreportmaking.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/test/{id}")
    public Student getStudent(@PathVariable("id") Long id) {
        return studentService.findById(id);
    }

    @GetMapping(value = "/list")
    public List<StudentDTO> getStudents() {
        return studentService.getAll();
    }

    @GetMapping(value = "/report")
    public List<StudentDTOReport> getReports() {
        Map<String, Long> maps = new HashMap<>();
        for (StudentDTO studentDTO : studentService.getAll()) {
            maps.put(studentDTO.getEmail(), studentService.countNoOfStudent(studentDTO.getEmail()));

        }
        List<StudentDTOReport> list = new ArrayList<>();

        for (Map.Entry<String, Long> s : maps.entrySet()) {
            list.add(new StudentDTOReport(s.getValue(), s.getKey()));
        }
        return list;
    }

    @GetMapping(value = "/report2")
    public Map<String,Long> getReport2(){
        Map<String,Long> maps=new HashMap<>();
        for (StudentDTO studentDTO:studentService.getAll()){
            maps.put(studentDTO.getEmail(),studentService.countNoOfStudent(studentDTO.getEmail()));
        }
        return maps;
    }

}
