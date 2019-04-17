package com.azad.templatequickjob.repo;

import com.azad.templatequickjob.entity.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DivisionRepo extends JpaRepository<Division, Long> {
    //Optional<Division> findByDivisionName(String divisionName);
    Division findByDivisionName(String divisionName);
    boolean existsDivisionByDivisionName(String divisionName);
}
