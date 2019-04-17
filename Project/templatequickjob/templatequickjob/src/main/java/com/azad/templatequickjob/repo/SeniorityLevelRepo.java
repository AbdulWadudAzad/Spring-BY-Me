package com.azad.templatequickjob.repo;


import com.azad.templatequickjob.entity.SeniorityLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeniorityLevelRepo extends JpaRepository<SeniorityLevel, Long> {
    SeniorityLevel findBySeniority(String seniority);
    boolean existsSeniorityLevelBySeniority(String seniority);
}
