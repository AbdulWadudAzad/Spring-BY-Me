package com.azad.templatequickjob.repo;


import com.azad.templatequickjob.entity.Certification;

import com.azad.templatequickjob.entity.User;
import com.azad.templatequickjob.entity.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificationRepo extends JpaRepository<Certification, Long> {
    Certification findByCertificName(String certificName);
    boolean existsCertificationByCertificName(String certificName);
    List<Certification> findAllByUser(User user);
}
