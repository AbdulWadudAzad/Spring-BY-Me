package com.azad.templatequickjob.repo;


import com.azad.templatequickjob.entity.JobFunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobFunctionRepo extends JpaRepository<JobFunction, Long> {
    JobFunction findByFunctionName(String functionName);
    boolean existsJobFunctionByFunctionName(String functionName);
}
