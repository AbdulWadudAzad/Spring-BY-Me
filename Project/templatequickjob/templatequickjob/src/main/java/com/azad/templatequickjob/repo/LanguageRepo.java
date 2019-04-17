package com.azad.templatequickjob.repo;

import com.azad.templatequickjob.entity.Language;
import com.azad.templatequickjob.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepo extends JpaRepository<Language, Long> {
    Language findByLanguageName(String languageName);
    boolean existsLanguageByLanguageName(String languageName);
    List<Language> findAllByUser(User user);
}
