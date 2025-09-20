package com.example.url_shortener.Repository;

import com.example.url_shortener.Model.AccessLog;
import com.example.url_shortener.Model.UrlRecord;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UrlRepository {

    private final Map<String, UrlRecord> urlStore = new ConcurrentHashMap<>();
    private final Map<String, List<AccessLog>> statsStore = new ConcurrentHashMap<>();

    public void saveMapping(String shortcode, UrlRecord mapping) {
        urlStore.put(shortcode, mapping);
        statsStore.put(shortcode, new ArrayList<>());
    }

    public UrlRecord findMapping(String shortcode) {
        return urlStore.get(shortcode);
    }

    public boolean exists(String shortcode) {
        return urlStore.containsKey(shortcode);
    }

    public void saveStat(String shortcode, AccessLog stat) {
        statsStore.get(shortcode).add(stat);
    }

    public List<AccessLog> findStats(String shortcode) {
        return statsStore.getOrDefault(shortcode, new ArrayList<>());
    }
}
