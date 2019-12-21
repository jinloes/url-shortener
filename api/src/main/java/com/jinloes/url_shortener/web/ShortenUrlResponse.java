package com.jinloes.url_shortener.web;

import lombok.Data;

@Data
public class ShortenUrlResponse {
    private final String shortUrl;
    private final String originalUrl;
}
