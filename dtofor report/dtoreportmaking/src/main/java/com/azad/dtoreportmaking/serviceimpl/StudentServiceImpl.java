package com.azad.dtoreportmaking.serviceimpl;

import com.azad.dtoreportmaking.dto.StudentDTO;
import com.azad.dtoreportmaking.entity.Student;
import com.azad.dtoreportmaking.repository.StudentRepo;
import com.azad.dtoreportmaking.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepo studentRepo;

    public Student convertDOTtoEntity(StudentDTO dot){
        Student student=new Student();
        student.setName(dot.getName());
        student.setEmail(dot.getEmail());
        return student;
    }
    public StudentDTO convertEntitytoDOT(Student student){
        StudentDTO dto=new StudentDTO();
        student.setId(student.getId());
        student.setName(student.getName());
        student.setEmail(student.getEmail());
        return dto;
    }

    @Override
    public void saveOrUpdate(StudentDTO studentDTO) {

        studentRepo.save(convertDOTtoEntity(studentDTO));
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
    public List<StudentDTO> getAll() {
   List<StudentDTO> dot=new ArrayList<>();
   List<Student> list=studentRepo.findAll();
   for(Student student:list){
       dot.add(convertEntitytoDOT(student));
   }
   return dot;
    }

    @Override
    public long countNoOfStudent(String email) {
        return studentRepo.countAllByEmail(email);
    }
}
