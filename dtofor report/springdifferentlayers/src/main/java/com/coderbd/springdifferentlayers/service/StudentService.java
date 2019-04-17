package com.coderbd.springdifferentlayers.service;

import com.coderbd.springdifferentlayers.dto.StudentDTO;
import com.coderbd.springdifferentlayers.entity.Student;

import java.util.List;

public interface StudentService {

    void saveOrUpdate(StudentDTO studentDTO);

    void deleteById(Long id);

    Student findById(Long id);

    Student findByEmailAddress(String email);

    List<StudentDTO> getAll();

    long countNoOfStudents(String email);

}
