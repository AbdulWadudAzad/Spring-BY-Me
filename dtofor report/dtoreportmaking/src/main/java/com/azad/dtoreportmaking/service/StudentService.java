package com.azad.dtoreportmaking.service;

import com.azad.dtoreportmaking.dto.StudentDTO;
import com.azad.dtoreportmaking.entity.Student;

import java.util.List;

public interface StudentService {
    void saveOrUpdate(StudentDTO studentDTO);

    void deleteById(Long id);
    Student findById(Long id);
    Student findByEmail(String email);
    List<StudentDTO> getAll();

    long countNoOfStudent(String email);
}
