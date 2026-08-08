package com.shorten.url.services;

import com.shorten.url.dto.ShortenUrlRequest;
import com.shorten.url.dto.ShortenUrlResponse;
import com.shorten.url.entities.UrlEntity;
import com.shorten.url.repositories.UrlRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;

@Service
public class UrlService {
    private final UrlRepository repo;

    public UrlService(UrlRepository repo) {
        this.repo = repo;
    }
    public ShortenUrlResponse shortenUrl(ShortenUrlRequest request, HttpServletRequest servletRequest){
        String id;
        do{
            id = RandomStringUtils.insecure().nextAlphanumeric(5,10);
        }while (repo.existsById(id));
        repo.save(new UrlEntity(id, request.url(), LocalDateTime.now().plusMinutes(100)));
        String redirect = servletRequest.getRequestURL().toString().replace("shorten-url",id);
        return new ShortenUrlResponse(redirect);
    }
    public HttpHeaders redirect(String id){
        UrlEntity url = repo.findById(id).orElseThrow();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.getFullUrl()));
        return headers;
    }
}
