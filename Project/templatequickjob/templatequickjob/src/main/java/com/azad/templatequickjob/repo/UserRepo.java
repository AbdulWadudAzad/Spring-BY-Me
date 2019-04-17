package com.azad.templatequickjob.repo;

import com.azad.templatequickjob.entity.Role;
import com.azad.templatequickjob.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUserNameOrEmail(String userName, String email);

    Optional<User> findByEmail(String email);

    List<User> findAllByRoles(Set<Role> roles);

    boolean existsByEmail(String email);

    User findByConfirmationToken(String token);

    User findByUserName(String userName);

    //User findByUserName(String username);
    // User findByEmail(String email);
    // Optional<User> findByUserName(String username);
}
