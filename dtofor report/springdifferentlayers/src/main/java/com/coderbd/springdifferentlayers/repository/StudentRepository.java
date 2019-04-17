package com.coderbd.springdifferentlayers.repository;

import com.coderbd.springdifferentlayers.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmail(String email);
    long countAllByEmail(String email);
}
