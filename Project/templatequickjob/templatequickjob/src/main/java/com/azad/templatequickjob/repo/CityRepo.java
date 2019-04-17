package com.azad.templatequickjob.repo;


import com.azad.templatequickjob.entity.City;
import com.azad.templatequickjob.entity.Distric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepo extends JpaRepository<City, Long> {
    City findByDistric(Distric distric);
    //boolean existsCityByCityName(String cityName);
}
