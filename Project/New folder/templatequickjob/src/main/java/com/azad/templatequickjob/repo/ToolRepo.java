package com.azad.templatequickjob.repo;


import com.azad.templatequickjob.entity.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolRepo extends JpaRepository<Tool, Long> {
    Tool findByToolName(String toolName);
    boolean existsTooleByToolName(String toolName);
}
