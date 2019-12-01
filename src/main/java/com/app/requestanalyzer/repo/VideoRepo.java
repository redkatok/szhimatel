package com.app.requestanalyzer.repo;

import com.app.requestanalyzer.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VideoRepo extends JpaRepository<Video, String> {
    @Query("select v.client_id from Video v where v.videoName=:name")
    List<Long> getClientsIds(String name);

    Video findByVideoName (String videoName);
}
