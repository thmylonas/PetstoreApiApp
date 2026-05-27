package com.thomasmylonas.petstore_api_app;

import com.thomasmylonas.petstore_api_app.helpers.TestDataProvider;
import com.thomasmylonas.petstore_api_app.services.OrderService;
import com.thomasmylonas.petstore_api_app.services.PetService;
import com.thomasmylonas.petstore_api_app.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * SELECT * FROM CATEGORIES;
 * SELECT * FROM ORDERS;
 * SELECT * FROM PETS;
 * SELECT * FROM PHOTO_URLS;
 * SELECT * FROM TAGS;
 * SELECT * FROM USERS;
 */
@SpringBootApplication
@RequiredArgsConstructor
@PropertySource("classpath:properties/properties.properties")
public class PetstoreApiAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetstoreApiAppApplication.class, args);
    }

    @Bean
    @Profile(value = {"dev"})
    public CommandLineRunner commandLineRunner(PetService petService, OrderService orderService, UserService userService) {
        return args -> {
            petService.saveAllPets(TestDataProvider.PET_REQUEST_DTOS);
            orderService.saveAllOrders(TestDataProvider.ORDER_REQUEST_DTOS);
            userService.saveAllUsers(TestDataProvider.USER_REQUEST_DTOS);
        };
    }
}
