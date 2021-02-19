package com.exampletaskiter.taskiter.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSoureConfig {


    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {

        return DataSourceBuilder.create().build();
    }

}
