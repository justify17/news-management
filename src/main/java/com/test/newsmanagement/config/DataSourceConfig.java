package com.test.newsmanagement.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @Profile("production")
    public DataSource postgresqlConnection() {

        return DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5432/news_management")
                .username("postgres")
                .password("admin")
                .build();
    }

    @Bean
    @Profile("development")
    public DataSource h2Connection() {
        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();

        return embeddedDatabaseBuilder
                .setType(EmbeddedDatabaseType.H2)
                .setName("devDb")
                .build();
    }
}