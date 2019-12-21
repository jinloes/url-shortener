package com.jinloes.url_shortener.web;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ShortenUrlRequest {

    private final String originalUrl;

    @JsonCreator
    public ShortenUrlRequest(@JsonProperty("original_url") String originalUrl) {
        this.originalUrl = originalUrl;
    }
}
