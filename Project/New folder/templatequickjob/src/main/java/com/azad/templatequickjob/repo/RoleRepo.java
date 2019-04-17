package com.azad.templatequickjob.repo;


import com.azad.templatequickjob.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);
    boolean existsRoleByRoleName(String roleName);
}
