package com.example.url_shortener.Controller;

import com.example.url_shortener.Model.ShortUrlRequest;
import com.example.url_shortener.Model.ShortUrlResponse;
import com.example.url_shortener.Model.UrlStatsResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.url_shortener.Service.UrlService;


@RestController
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorturls")
    public ResponseEntity<ShortUrlResponse> createShortUrl(
            @RequestBody ShortUrlRequest request,
            HttpServletRequest servletRequest) {
        return urlService.createShortUrl(request, servletRequest);
    }

    @GetMapping("/{shortcode}")
    public ResponseEntity<Void> redirectToOriginalUrl(
            @PathVariable String shortcode,
            HttpServletRequest request) {
        return urlService.redirectToOriginalUrl(shortcode, request);
    }

    @GetMapping("/shorturls/{shortcode}")
    public ResponseEntity<UrlStatsResponse> getShortUrlStatistics(@PathVariable String shortcode) {
        return urlService.getShortUrlStatistics(shortcode);
    }
}
