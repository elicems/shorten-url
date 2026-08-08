package com.shorten.url.controller;

import com.shorten.url.UrlApplication;
import com.shorten.url.dto.ShortenUrlRequest;
import com.shorten.url.dto.ShortenUrlResponse;
import com.shorten.url.entities.UrlEntity;
import com.shorten.url.repositories.UrlRepository;
import com.shorten.url.services.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
public class UrlController {
    private final UrlService service;
    private final UrlRepository repo;

    public UrlController(UrlService service, UrlRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    @PostMapping(value = "/shorten-url")
    public ResponseEntity<ShortenUrlResponse> shortenUrl(@RequestBody ShortenUrlRequest request,
                                                         HttpServletRequest servletRequest) {
       var redirect = service.shortenUrl(request,servletRequest);
       return ResponseEntity.ok(redirect);
    }

    @GetMapping("{id}")
    public ResponseEntity<Void> redirect(@PathVariable("id") String id) {
        var redirect = service.redirect(id);
        return ResponseEntity.status(HttpStatus.FOUND).headers(redirect).build();
    }
}
