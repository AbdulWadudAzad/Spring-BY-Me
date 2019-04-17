package com.azad.croudsqlthymleaf.repository;

import com.azad.croudsqlthymleaf.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
Role findByRoleName(String roleName);
}
