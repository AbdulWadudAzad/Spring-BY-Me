package com.coderbd.webmvcthymelaef.repository;

import com.coderbd.webmvcthymelaef.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
}
