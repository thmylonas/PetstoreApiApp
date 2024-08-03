package com.thomasmylonas.petstore_api_web_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SELECT * FROM CATEGORIES;
 * SELECT * FROM ORDERS;
 * SELECT * FROM PETS;
 * SELECT * FROM TAGS;
 * SELECT * FROM USERS;
 */
@SpringBootApplication
public class PetstoreApiWebAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetstoreApiWebAppApplication.class, args);
    }
}
