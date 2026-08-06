package com.shorten.url.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;

@Document(collection = "urls")
public class UrlEntity {
    @Id
    private String id;
    private String fullUrl;

    @Indexed(expireAfter = "0")
    private LocalDateTime expiresAt;
}
