package com.jinloes.url_shortener.service;

import com.jinloes.url_shortener.data.model.UrlMapping;
import com.jinloes.url_shortener.data.repository.UrlMappingRepo;
import com.jinloes.url_shortener.util.Base62;
import com.jinloes.url_shortener.util.UuidUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlMappingService {
    private final UrlMappingRepo urlMappingRepo;
    private final String frontEndUrl;

    public UrlMappingService(UrlMappingRepo urlMappingRepo, @Value("${frontend.url}") String frontEndUrl) {
        this.urlMappingRepo = urlMappingRepo;
        this.frontEndUrl = frontEndUrl;
    }

    public UrlMapping create(String originalUrl) {
        String code = Base62.encodeToString(UuidUtils.asBigInt());
        String shortUrl = frontEndUrl + "/" + code;
        UrlMapping urlMapping = new UrlMapping(originalUrl, code, shortUrl);
        return urlMappingRepo.save(urlMapping);
    }

    public Optional<UrlMapping> get(String code) {
        return urlMappingRepo.findById(code);
    }
}
