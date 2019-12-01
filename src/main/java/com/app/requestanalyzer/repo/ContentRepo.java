package com.app.requestanalyzer.repo;

import com.app.requestanalyzer.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepo extends JpaRepository<Content, Long> {

    Content findByName(String name);
}
