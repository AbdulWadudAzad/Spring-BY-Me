package com.azad.dtoreportmaking.repository;

import com.azad.dtoreportmaking.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    Student findByEmail(String email);

    long countAllByEmail(String email);
}
