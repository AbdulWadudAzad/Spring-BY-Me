package com.azad.templatequickjob.repo;



import com.azad.templatequickjob.entity.NewsAboutUs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsAboutUsRepo extends JpaRepository<NewsAboutUs, Long> {
    NewsAboutUs findByHearAboutUs(String hearAboutUs);
    boolean existsNewsAboutUsByHearAboutUs(String hearAboutUs);
}
