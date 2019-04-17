package com.azad.dtoreport.repository;

import com.azad.dtoreport.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    Student findByEmail(String email);

    long countAllByEmail(String email);
}
