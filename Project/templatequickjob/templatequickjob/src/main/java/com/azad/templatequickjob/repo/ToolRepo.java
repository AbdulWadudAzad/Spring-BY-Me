package com.azad.templatequickjob.repo;


import com.azad.templatequickjob.entity.Tool;
import com.azad.templatequickjob.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToolRepo extends JpaRepository<Tool, Long> {
    Tool findByToolName(String toolName);
    boolean existsTooleByToolName(String toolName);
    List<Tool> findAllByUser(User user);

}
