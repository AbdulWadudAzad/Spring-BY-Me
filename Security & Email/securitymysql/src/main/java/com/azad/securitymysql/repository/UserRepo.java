package com.azad.securitymysql.repository;


import com.azad.securitymysql.entity.Role;
import com.azad.securitymysql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsernameOrEmail(String username, String email); //use for login by username or email
    User findByUsername(String username);
/////////////
    User findByUsernameOrName(String username, String name);
List<User> findAllByRoles(Set<Role> roles);
}