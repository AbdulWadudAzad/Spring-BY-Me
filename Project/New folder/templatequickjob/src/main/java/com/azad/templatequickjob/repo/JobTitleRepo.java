package com.azad.templatequickjob.repo;


import com.azad.templatequickjob.entity.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface JobTitleRepo extends JpaRepository<JobTitle, Long> {
    JobTitle findByJobTitleName(String jobTitleName);
   //Optional<JobTitle> findByJobTitleName(String jobTitleName);
    boolean existsJobTitleByJobTitleName(String jobTitleName);
}
