package com.app.requestanalyzer.controller;

import com.app.requestanalyzer.model.Client;
import com.app.requestanalyzer.model.Video;
import com.app.requestanalyzer.repo.ClientRepo;
import com.app.requestanalyzer.repo.ContentRepo;
import com.app.requestanalyzer.repo.VideoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/analyzer")
public class AnalyzerController {

    @Autowired
    private ContentRepo contentRepo;
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private VideoRepo videoRepo;

    private String value = "alpha";

    @GetMapping("/video/{name}")
    public ResponseEntity<MultipartFile[]> getResult(@PathVariable("name") String name, @RequestHeader("cookie") String clientId) {

        dblogic(name, clientId);

        List<Client> clients = videoRepo.findByVideoName(name).getClients();
        if (clients.size() == 2) {
            RestTemplate restTemplate = new RestTemplate();
            String link = videoRepo.findByVideoName(name).getVideoLink();
            MultipartFile[] result = restTemplate.getForObject(link, MultipartFile[].class);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

    //    внесение пользователя в бд и установка ему связи с искомым им  видео
    private void dblogic(String videoName, String clientId) {
        if (videoName == null && clientId == null) {
            return;
        }
        if (clientRepo.getClientByClientId(clientId) == null) {
            Video video = videoRepo.findByVideoName(videoName);
            List<Client> clients = video.getClients();
            clients.add(new Client(clientId));
            video.setClients(clients);
            videoRepo.save(video);
        }
    }

//    @GetMapping("/listHeaders")
//    public ResponseEntity<String> listAllHeaders(
//            @RequestHeader Map<String, String> headers) {
//        headers.forEach((key, value) -> {
//            System.out.println(String.format("Header '%s' = %s", key, value));
//        });
//
//        return new ResponseEntity<String>(
//                String.format("Listed %d headers", headers.size()), HttpStatus.OK);
//    }


}
