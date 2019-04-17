package com.azad.templatequickjob.repo;



import com.azad.templatequickjob.entity.Referance;
import com.azad.templatequickjob.entity.User;
import com.azad.templatequickjob.entity.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReferanceRepo extends JpaRepository<Referance, Long> {
    List<Referance> findAllByUser(User user);
}
