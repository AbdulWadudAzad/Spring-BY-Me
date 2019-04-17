package com.azad.templatequickjob.repo;



import com.azad.templatequickjob.entity.EmploymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentTypeRepo extends JpaRepository<EmploymentType, Long> {
    EmploymentType findByEmpType(String empType);
    boolean existsEmploymentTypeByEmpType(String empType);
}
