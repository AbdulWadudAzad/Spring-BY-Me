package com.azad.templatequickjob.repo;


import com.azad.templatequickjob.entity.User;
import com.azad.templatequickjob.entity.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkExperienceRepo extends JpaRepository<WorkExperience, Long> {
    WorkExperience findByJobTitle(String jobTitle);
    boolean existsWorkExperienceByJobTitle(String jobTitle);
    List<WorkExperience> findAllByUser(User user);
}
