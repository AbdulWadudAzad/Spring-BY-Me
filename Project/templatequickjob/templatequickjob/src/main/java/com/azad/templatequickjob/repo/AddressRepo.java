package com.azad.templatequickjob.repo;


import com.azad.templatequickjob.entity.Address;
import com.azad.templatequickjob.entity.User;
import com.azad.templatequickjob.entity.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {
    Address findByCountry_CountryName(String Country);
   // boolean existsEduLevelByLevelName(String levelName);
   List<Address> findAllByUser(User user);
}
