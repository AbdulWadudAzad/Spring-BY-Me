package com.azad.templatequickjob.repo;


import com.azad.templatequickjob.entity.EduSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EduSubjectRepo extends JpaRepository<EduSubject, Long> {
    EduSubject findBySubName(String subName);
    boolean existsEduSubjectBySubName(String subName);
}
