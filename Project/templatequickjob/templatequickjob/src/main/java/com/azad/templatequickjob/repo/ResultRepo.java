package com.azad.templatequickjob.repo;


import com.azad.templatequickjob.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepo extends JpaRepository<Result, Long> {
    Result findByResultName(String resultName);
    boolean existsResultByResultName(String resultName);
}
