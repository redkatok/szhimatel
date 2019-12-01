package com.app.requestanalyzer;

import com.app.requestanalyzer.model.Video;
import com.app.requestanalyzer.repo.ContentRepo;
import com.app.requestanalyzer.repo.VideoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class RequestAnalyzerApplication implements CommandLineRunner {
    @Autowired
    private ContentRepo contentRepo;

    @Autowired
    private VideoRepo videoRepo;

    public static void main(String[] args) {
        SpringApplication.run(RequestAnalyzerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Map<String, String> videoNameLink = new HashMap<>();
        videoNameLink.put("alpha", "https://www.youtube.com/watch?v=wakHdiFTUyg");
        videoNameLink.put("beta", "https://www.youtube.com/watch?v=pF77zLM3Zd4");
        videoNameLink.put("gamma", "https://www.youtube.com/watch?v=Gb0TQ7VeApY");
        for (Map.Entry<String, String> entry : videoNameLink.entrySet()) {
            Video video = new Video();
            video.setVideoName(entry.getKey());
            video.setVideoLink(entry.getValue());
            videoRepo.save(video);
        }

    }
}
