package com.azad.templatequickjob.repo;



import com.azad.templatequickjob.entity.Distric;
import com.azad.templatequickjob.entity.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistricRepo extends JpaRepository<Distric, Long> {
    Distric findByDivision(Division division);
    //boolean existsDistricByDistricName(String districName);
}
