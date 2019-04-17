package com.azad.templatequickjob.repo;


import com.azad.templatequickjob.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepo extends JpaRepository<City, Long> {
    City findByCityName(String cityName);
    boolean existsCityByCityName(String cityName);
}
