package com.example.url_shortener.Model;

public class ShortUrlRequest {
    private String url;
    private Integer validity;
    private String shortcode;

    public ShortUrlRequest() {
    }

    public ShortUrlRequest(String url, Integer validity, String shortcode) {
        this.url = url;
        this.validity = validity;
        this.shortcode = shortcode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getValidity() {
        return validity;
    }

    public void setValidity(Integer validity) {
        this.validity = validity;
    }

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }
}
