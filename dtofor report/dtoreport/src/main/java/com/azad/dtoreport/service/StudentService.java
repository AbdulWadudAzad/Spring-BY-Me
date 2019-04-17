package com.azad.dtoreport.service;

import com.azad.dtoreport.entity.Student;

import java.util.List;

public interface StudentService {
    void saveOrUpdate(Student student);

    void deleteById(Long id);
    Student findById(Long id);
    Student findByEmail(String email);
    List<Student> getAll();
}
