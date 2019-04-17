package com.azad.templatequickjob.repo;

import com.azad.templatequickjob.entity.Exam;
import com.azad.templatequickjob.entity.Role;
import com.azad.templatequickjob.entity.User;
import com.azad.templatequickjob.entity.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ExamRepo extends JpaRepository<Exam, Long> {
    //Exam findByEduLevel_LevelName(String levelName);
    List<Exam> findAllByUser(User user);

}
