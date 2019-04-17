package com.azad.croudsqlthymleaf.repository;

import com.azad.croudsqlthymleaf.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public interface UserRepo extends JpaRepository<User, Long> {
}