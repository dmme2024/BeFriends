package com.example.datebasebigwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EntityScan("com.example.datebasebigwork.Entity")
//@EnableJpaRepositories(basePackages = "com.example.datebasebigwork.Repository")
public class DatebasebigworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatebasebigworkApplication.class, args);
    }

}
