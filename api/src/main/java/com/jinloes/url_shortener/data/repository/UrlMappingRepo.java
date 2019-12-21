package com.jinloes.url_shortener.data.repository;

import com.jinloes.url_shortener.data.model.UrlMapping;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface UrlMappingRepo extends CassandraRepository<UrlMapping, String> {
}
