package com.example.url_shortener.Service;

import com.example.url_shortener.Model.*;
import com.example.url_shortener.Repository.UrlRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UrlService {


    private static final Logger log = LoggerFactory.getLogger(UrlService.class);
    private final UrlRepository repository;

    public UrlService(UrlRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<ShortUrlResponse> createShortUrl(
            ShortUrlRequest request, HttpServletRequest servletRequest) {

        log.info("Received request to shorten URL: {}", request.getUrl());

        String shortcode = (request.getShortcode() == null || request.getShortcode().isEmpty())
                ? generateShortCode()
                : request.getShortcode();

        while (repository.exists(shortcode)) {
            shortcode = generateShortCode();
        }

        long validityMinutes = (request.getValidity() != null) ? request.getValidity() : 30;
        Instant expiry = Instant.now().plus(validityMinutes, ChronoUnit.MINUTES);

        UrlRecord mapping = new UrlRecord(request.getUrl(), Instant.now(), expiry);
        repository.saveMapping(shortcode, mapping);

        String baseUrl = servletRequest.getRequestURL().toString()
                .replace(servletRequest.getRequestURI(), "");
        String shortLink = baseUrl + "/" + shortcode;

        ShortUrlResponse response = new ShortUrlResponse(shortLink, expiry);
        log.info("Successfully created short link: {}", shortLink);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<Void> redirectToOriginalUrl(String shortcode, HttpServletRequest request) {
        UrlRecord mapping = repository.findMapping(shortcode);

        if (mapping == null || Instant.now().isAfter(mapping.getExpiry())) {
            log.warn("Shortcode '{}' not found or has expired.", shortcode);
            return ResponseEntity.notFound().build();
        }

        AccessLog stat = new AccessLog(
                Instant.now(),
                request.getHeader("User-Agent"),
                request.getRemoteAddr(),
                request.getHeader(HttpHeaders.REFERER)
        );
        repository.saveStat(shortcode, stat);

        log.info("Redirecting shortcode '{}' to its original URL.", shortcode);

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(mapping.getOriginalUrl()))
                .build();
    }

    public ResponseEntity<UrlStatsResponse> getShortUrlStatistics(String shortcode) {
        UrlRecord mapping = repository.findMapping(shortcode);

        if (mapping == null) {
            log.warn("Statistics requested for non-existent shortcode '{}'", shortcode);
            return ResponseEntity.notFound().build();
        }

        List<AccessLog> stats = repository.findStats(shortcode);
        List<UrlStatsResponse.ClickStatDto> clickDetails = stats.stream()
                .map(stat -> new UrlStatsResponse.ClickStatDto(
                        stat.getTimestamp(), stat.getSourceIp(), stat.getReferrer()))
                .collect(Collectors.toList());

        UrlStatsResponse response = new UrlStatsResponse(
                stats.size(),
                mapping.getOriginalUrl(),
                mapping.getCreationDate(),
                clickDetails
        );

        return ResponseEntity.ok(response);
    }

    private String generateShortCode() {
        String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom RANDOM = new SecureRandom();
        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            sb.append(CHARS.charAt(RANDOM.nextInt(CHARS.length())));
        }
        return sb.toString();
    }
}
