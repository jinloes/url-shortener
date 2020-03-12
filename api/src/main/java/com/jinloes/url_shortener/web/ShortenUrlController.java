package com.jinloes.url_shortener.web;

import com.jinloes.url_shortener.data.model.UrlMapping;
import com.jinloes.url_shortener.service.UrlMappingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/url_shortener")
public class ShortenUrlController {
    private final UrlMappingService urlMappingService;

    public ShortenUrlController(UrlMappingService urlMappingService) {
        this.urlMappingService = urlMappingService;
    }

    @GetMapping("/{code}")
    public ResponseEntity<ShortenUrlResponse> get(@PathVariable("code") String code) {
        UrlMapping urlMapping = urlMappingService.get(code)
                .orElseThrow(() -> new RuntimeException("Not found"));
        return ResponseEntity.ok(new ShortenUrlResponse(urlMapping.getShortUrl(), urlMapping.getOriginalUrl()));
    }

    @PostMapping
    public ResponseEntity<ShortenUrlResponse> create(@RequestBody ShortenUrlRequest request) {
        UrlMapping urlMapping = urlMappingService.create(request.getOriginalUrl());
        return ResponseEntity.status(201)
                .body(new ShortenUrlResponse(urlMapping.getShortUrl(), urlMapping.getOriginalUrl()));
    }
}
