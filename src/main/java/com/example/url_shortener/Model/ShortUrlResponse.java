package com.example.url_shortener.Model;

import java.time.Instant;

public class ShortUrlResponse {
    private String shortLink;
    private Instant expiry;

    public ShortUrlResponse() {
    }

    public ShortUrlResponse(String shortLink, Instant expiry) {
        this.shortLink = shortLink;
        this.expiry = expiry;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    public Instant getExpiry() {
        return expiry;
    }

    public void setExpiry(Instant expiry) {
        this.expiry = expiry;
    }
}
