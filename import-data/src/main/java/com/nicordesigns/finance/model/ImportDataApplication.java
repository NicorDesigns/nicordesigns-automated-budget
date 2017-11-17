package com.nicordesigns.finance.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImportDataApplication {

    @Autowired
    DataLoader dataLoader;

    public static void main(String[] args) {
        SpringApplication.run(ImportDataApplication.class, args);
    }
}

