package com.azad.templatequickjob.repo;


import com.azad.templatequickjob.entity.Certification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificationRepo extends JpaRepository<Certification, Long> {
    Certification findByCertificName(String certificName);
    boolean existsCertificationByCertificName(String certificName);
}
