package com.azad.templatequickjob.repo;


import com.azad.templatequickjob.entity.City;
import com.azad.templatequickjob.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepo extends JpaRepository<Language, Long> {
    Language findByLanguageName(String languageName);
    boolean existsLanguageByLanguageName(String languageName);
}
