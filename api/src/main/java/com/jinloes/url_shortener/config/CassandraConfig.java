package com.jinloes.url_shortener.config;

import com.google.common.collect.Lists;
import com.jinloes.url_shortener.data.model.ModelPackage;
import com.jinloes.url_shortener.data.repository.RepositoryPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.EnableCassandraAuditing;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.List;

@Configuration
@EnableCassandraRepositories(basePackageClasses = RepositoryPackage.class)
@EnableCassandraAuditing
public class CassandraConfig extends AbstractCassandraConfiguration {

    private final CassandraProperties properties;

    @Autowired
    public CassandraConfig(CassandraProperties properties) {
        this.properties = properties;
    }

    @Override
    public List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        return Lists.newArrayList(
                CreateKeyspaceSpecification.createKeyspace(getKeyspaceName())
                        .ifNotExists());
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[]{ModelPackage.class.getPackage().getName()};
    }

    @Override
    protected String getKeyspaceName() {
        return "testKeySpace";
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.valueOf(properties.getSchemaAction().toUpperCase());
    }
}