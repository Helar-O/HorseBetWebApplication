package com.example.telia.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HsqlDataSource {

    @Bean
    public Connection hsqlServer() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:data/db", "sa", "");
    }

    @Bean
    @DependsOn("hsqlServer")
    public DataSource dataSource(@Autowired DataSourceProperties dsProps) {
        DataSourceBuilder<?> ds = DataSourceBuilder.create();
        ds.driverClassName(dsProps.getDriverClassName());
        ds.url(dsProps.getUrl());
        ds.username(dsProps.getUsername());
        ds.password(dsProps.getPassword());
        return ds.build();
    }
}
