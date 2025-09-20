package com.example.url_shortener.Model;

import java.time.Instant;
import java.util.List;

public class UrlStatsResponse {
    private int totalClicks;
    private String originalUrl;
    private Instant creationDate;
    private List<ClickStatDto> clickDetails;

    public UrlStatsResponse() {
    }

    public UrlStatsResponse(int totalClicks, String originalUrl, Instant creationDate, List<ClickStatDto> clickDetails) {
        this.totalClicks = totalClicks;
        this.originalUrl = originalUrl;
        this.creationDate = creationDate;
        this.clickDetails = clickDetails;
    }

    public int getTotalClicks() {
        return totalClicks;
    }

    public void setTotalClicks(int totalClicks) {
        this.totalClicks = totalClicks;
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

    public List<ClickStatDto> getClickDetails() {
        return clickDetails;
    }

    public void setClickDetails(List<ClickStatDto> clickDetails) {
        this.clickDetails = clickDetails;
    }

    public static class ClickStatDto {
        private Instant timestamp;
        private String source;
        private String referrer;

        public ClickStatDto() {
        }

        public ClickStatDto(Instant timestamp, String source, String referrer) {
            this.timestamp = timestamp;
            this.source = source;
            this.referrer = referrer;
        }

        public Instant getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Instant timestamp) {
            this.timestamp = timestamp;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getReferrer() {
            return referrer;
        }

        public void setReferrer(String referrer) {
            this.referrer = referrer;
        }
    }
}
