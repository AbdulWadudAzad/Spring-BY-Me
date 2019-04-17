package com.azad.templatequickjob.repo;


import com.azad.templatequickjob.entity.Address;
import com.azad.templatequickjob.entity.JobPost;
import com.azad.templatequickjob.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobPostRepo extends JpaRepository<JobPost, Long> {
    JobPost findByCompanyName(String companyName);
   // boolean existsEduLevelByLevelName(String companyName);
   List<JobPost> findAllByUser(User user);
 }
