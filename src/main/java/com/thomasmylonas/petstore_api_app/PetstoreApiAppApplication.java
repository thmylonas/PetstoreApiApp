package com.thomasmylonas.petstore_api_app;

import com.thomasmylonas.petstore_api_app.dtos.PetRequestDto;
import com.thomasmylonas.petstore_api_app.entities.Category;
import com.thomasmylonas.petstore_api_app.enums.PetStatus;
import com.thomasmylonas.petstore_api_app.services.PetService;
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
public class PetstoreApiAppApplication {

    private final PetService petService;

    public static void main(String[] args) {
        SpringApplication.run(PetstoreApiAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args ->
                petService.saveAllPets(List.of(
                                PetRequestDto.builder()
                                        .name("Doggie")
                                        .category(Category.builder()
                                                .name("Dog")
                                                .build())
                                        .status(PetStatus.AVAILABLE)
                                        .build()
                        )
                );
    }
}
