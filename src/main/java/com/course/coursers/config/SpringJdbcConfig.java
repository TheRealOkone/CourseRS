package com.course.coursers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan
public class SpringJdbcConfig {
    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgres://qzdhjgztbhxcft:b75d541f11a138fb9f037315ec2eaf1c92b099a42349c6c960d9fc742aaf368d@ec2-52-48-159-67.eu-west-1.compute.amazonaws.com:5432/d9569fjoc1v0la");
        dataSource.setUsername("qzdhjgztbhxcft");
        dataSource.setPassword("b75d541f11a138fb9f037315ec2eaf1c92b099a42349c6c960d9fc742aaf368d");

        return dataSource;
    }
}
