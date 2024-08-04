package com.thomasmylonas.petstore_api_web_app;

import com.thomasmylonas.petstore_api_web_app.data_access_layer.entities.Category;
import com.thomasmylonas.petstore_api_web_app.data_access_layer.entities.Pet;
import com.thomasmylonas.petstore_api_web_app.service_layer.models.enums.PetStatusEnum;
import com.thomasmylonas.petstore_api_web_app.service_layer.services.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

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
public class PetstoreApiWebAppApplication {

    private final PetService petService;

    public static void main(String[] args) {
        SpringApplication.run(PetstoreApiWebAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args ->
                petService.savePetsInBatch(List.of(
                        Pet.builder()
                                .name("Doggie")
                                .category(Category.builder()
                                        .name("Dog")
                                        .build())
                                .status(PetStatusEnum.AVAILABLE)
                                .build()
                ));
    }
}
