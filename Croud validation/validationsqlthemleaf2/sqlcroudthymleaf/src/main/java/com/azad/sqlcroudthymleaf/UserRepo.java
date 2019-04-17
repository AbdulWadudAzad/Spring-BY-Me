package com.azad.sqlcroudthymleaf;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public interface UserRepo extends JpaRepository<User, Long> {
}
