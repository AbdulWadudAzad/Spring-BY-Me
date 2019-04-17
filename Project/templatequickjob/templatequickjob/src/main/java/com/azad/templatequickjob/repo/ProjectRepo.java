package com.azad.templatequickjob.repo;



import com.azad.templatequickjob.entity.Project;
import com.azad.templatequickjob.entity.User;
import com.azad.templatequickjob.entity.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {
    Project findByProjectName(String projectName);
    boolean existsProjectByProjectName(String projectName);
    List<Project> findAllByUser(User user);
}
