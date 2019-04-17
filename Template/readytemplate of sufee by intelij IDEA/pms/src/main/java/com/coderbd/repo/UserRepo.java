package com.coderbd.repo;

import com.coderbd.entity.Role;
import com.coderbd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUserNameOrEmail(String userName, String email);
    User findByUserName(String username);
     List<User> findAllByRoles(Set<Role> roles);
}
