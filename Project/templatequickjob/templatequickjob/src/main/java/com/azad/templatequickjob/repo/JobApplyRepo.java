package com.azad.templatequickjob.repo;


import com.azad.templatequickjob.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplyRepo extends JpaRepository<JobApply, Long> {


}
