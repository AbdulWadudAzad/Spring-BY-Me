package com.azad.templatequickjob.repo;


import com.azad.templatequickjob.entity.CompanyIndustry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyIndustryRepo extends JpaRepository<CompanyIndustry, Long> {
    CompanyIndustry findByComIndustry(String comIndustry);
    boolean existsCompanyIndustryByComIndustry(String comIndustry);
}
