package com.example.url_shortener.Model;

import java.time.Instant;

public class AccessLog {
    private Instant timestamp;
    private String userAgent;
    private String sourceIp;
    private String referrer;

    public AccessLog() {
    }

    public AccessLog(Instant timestamp, String userAgent, String sourceIp, String referrer) {
        this.timestamp = timestamp;
        this.userAgent = userAgent;
        this.sourceIp = sourceIp;
        this.referrer = referrer;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }
}
