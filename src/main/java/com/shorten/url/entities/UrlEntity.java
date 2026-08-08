package com.shorten.url.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

@Document(collection = "urls")
public class UrlEntity {
    @Id
    private String id;
    private String fullUrl;

    @Indexed(expireAfter = "0")
    private LocalDateTime expiresAt;

    public UrlEntity() {
    }

    public UrlEntity(String id, String fullUrl, LocalDateTime expiresAt) {
        this.id = id;
        this.fullUrl = fullUrl;
        this.expiresAt = expiresAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UrlEntity urlEntity = (UrlEntity) o;
        return Objects.equals(id, urlEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
