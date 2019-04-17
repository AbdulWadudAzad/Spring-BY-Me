package com.azad.templatequickjob.repo;


import com.azad.templatequickjob.entity.Country;
import com.azad.templatequickjob.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepo extends JpaRepository<Country, Long> {
    Country findByCountryName(String countryName);
    //Optional<Country> findByCountryName(String countryName);
    boolean existsCountryByCountryName(String countryName);
}
