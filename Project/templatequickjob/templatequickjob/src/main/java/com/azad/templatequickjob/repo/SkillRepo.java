package com.azad.templatequickjob.repo;


import com.azad.templatequickjob.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepo extends JpaRepository<Skill, Long> {
    Skill findBySkillName(String skillName);
    boolean existsSkillBySkillName(String skillName);
}
