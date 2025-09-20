package com.example.url_shortener.Model;

import java.time.Instant;

public class UrlRecord {
    private String originalUrl;
    private Instant creationDate;
    private Instant expiry;

    public UrlRecord() {
    }

    public UrlRecord(String originalUrl, Instant creationDate, Instant expiry) {
        this.originalUrl = originalUrl;
        this.creationDate = creationDate;
        this.expiry = expiry;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Instant getExpiry() {
        return expiry;
    }

    public void setExpiry(Instant expiry) {
        this.expiry = expiry;
    }
}
