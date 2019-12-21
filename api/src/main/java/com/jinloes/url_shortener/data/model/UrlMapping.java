package com.jinloes.url_shortener.data.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.domain.Auditable;

import java.time.Instant;
import java.util.Optional;

@Data
@Table("url_mapping")
public class UrlMapping implements Auditable<String, String, Instant> {
    @Column("original_url")
    private String originalUrl;

    @PrimaryKey
    @Column("url_code")
    private String urlCode;

    @Column("short_url")
    private String shortUrl;

    @CreatedDate
    @Column("created_date")
    private Instant createdDate;

    @LastModifiedDate
    @Column("updated_date")
    private Instant lastModifiedDate;

    public UrlMapping(String originalUrl, String urlCode, String shortUrl) {
        this.originalUrl = originalUrl;
        this.urlCode = urlCode;
        this.shortUrl = shortUrl;
    }

    @Override
    public Optional<String> getCreatedBy() {
        return Optional.empty();
    }

    @Override
    public Optional<Instant> getCreatedDate() {
        return Optional.ofNullable(createdDate);
    }

    @Override
    public void setCreatedBy(String createdBy) {

    }

    @Override
    public Optional<String> getLastModifiedBy() {
        return Optional.empty();
    }

    @Override
    public Optional<Instant> getLastModifiedDate() {
        return Optional.ofNullable(lastModifiedDate);
    }

    @Override
    public void setLastModifiedBy(String lastModifiedBy) {

    }

    @Override
    public String getId() {
        return originalUrl;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
