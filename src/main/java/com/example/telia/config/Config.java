package com.example.telia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class Config {

    @Bean
    public JdbcTemplate getTemplate(DataSource ds) {
        return new JdbcTemplate(ds);
    }
}
