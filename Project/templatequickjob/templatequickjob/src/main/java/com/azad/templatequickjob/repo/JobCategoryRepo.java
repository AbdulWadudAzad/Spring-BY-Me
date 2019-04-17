package com.azad.templatequickjob.repo;


import com.azad.templatequickjob.entity.JobCategory;
import com.azad.templatequickjob.entity.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobCategoryRepo extends JpaRepository<JobCategory, Long> {
    JobCategory findByJobCategoryName(String jobCategoryName);
   //Optional<JobTitle> findByJobTitleName(String jobTitleName);
    boolean existsJobCategoryByJobCategoryName(String jobCategoryName);
}
