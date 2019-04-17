package com.azad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
    @Bean(name = "student")
    public Student getstudent() {
        Student student = new Student(100, "Azad"); //no need to write id: and name: it will create auto
        return student;
    }

    @Bean(name = "student2")
    public Student getStudentBySetter() {
        Student student = new Student();
        student.setId(500);
        student.setName("Azad");
        return student;
    }
}
