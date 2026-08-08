package com.shorten.url.controller;

import com.shorten.url.dto.ShortenUrlRequest;
import com.shorten.url.dto.ShortenUrlResponse;
import com.shorten.url.entities.UrlEntity;
import com.shorten.url.repositories.UrlRepository;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class UrlController {
    private final UrlRepository repo;

    public UrlController(UrlRepository repo) {
        this.repo = repo;
    }

    @PostMapping(value = "/shorten-url")
    public ResponseEntity<ShortenUrlResponse> shortenUrl(@RequestBody ShortenUrlRequest request,
                                                HttpServletRequest servletRequest){
        String id;
        do{
            id = RandomStringUtils.insecure().nextAlphanumeric(5,10);
        }while (repo.existsById(id));
        repo.save(new UrlEntity(id,request.url(), LocalDateTime.now().plusMinutes(1)));
        String redirectUrl = servletRequest.getRequestURL().toString().replace("shorten-url",id);
        return ResponseEntity.ok(new ShortenUrlResponse(redirectUrl));
    }
}
