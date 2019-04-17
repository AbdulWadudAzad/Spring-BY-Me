package com.azad.templatequickjob.repo;


import com.azad.templatequickjob.entity.Skill;
import com.azad.templatequickjob.entity.YearExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YearExperienceRepo extends JpaRepository<YearExperience, Long> {
    YearExperience findByYear(String year);
    boolean existsYearExperienceByYear(String year);
}
