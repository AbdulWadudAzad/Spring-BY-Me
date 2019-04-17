package com.azad.templatequickjob.repo;


import com.azad.templatequickjob.entity.EduLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EduLevelRepo extends JpaRepository<EduLevel, Long> {
    EduLevel findByLevelName(String levelName);
    boolean existsEduLevelByLevelName(String levelName);
}
