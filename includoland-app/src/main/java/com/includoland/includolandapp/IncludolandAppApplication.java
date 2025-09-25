package com.includoland.includolandapp;

import com.includoland.includolandliquibase.config.IncludolandLiquibaseConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(IncludolandLiquibaseConfig.class)
@SpringBootApplication
public class IncludolandAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(IncludolandAppApplication.class, args);
    }

}
