package com.azad.dtoreport.serviceimpl;

import com.azad.dtoreport.entity.Student;
import com.azad.dtoreport.repository.StudentRepo;
import com.azad.dtoreport.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepo studentRepo;

    @Override
    public void saveOrUpdate(Student student) {
        studentRepo.save(student);
    }

    @Override
    public void deleteById(Long id) {
        studentRepo.deleteById(id);
    }

    @Override
    public Student findById(Long id) {
        return studentRepo.getOne(id);
    }

    @Override
    public Student findByEmail(String email) {
        return studentRepo.findByEmail(email);
    }

    @Override
    public List<Student> getAll() {
        return studentRepo.findAll();
    }
}
