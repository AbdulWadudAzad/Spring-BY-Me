package com.azad.templatequickjob.repo;


import com.azad.templatequickjob.entity.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkExperienceRepo extends JpaRepository<WorkExperience, Long> {
    WorkExperience findByYear(String year);
    boolean existsWorkExperienceByYear(String year);
}
