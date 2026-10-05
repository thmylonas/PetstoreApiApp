package com.thomasmylonas.petstore_api_app.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thomasmylonas.petstore_api_app.api.dtos.category_dtos.CategoryResponseDto;
import com.thomasmylonas.petstore_api_app.api.dtos.pet_dtos.PetResponseDto;
import com.thomasmylonas.petstore_api_app.api.dtos.photo_url_dtos.PhotoUrlResponseDto;
import com.thomasmylonas.petstore_api_app.api.dtos.tag_dtos.TagResponseDto;
import com.thomasmylonas.petstore_api_app.enums.PetStatus;
import com.thomasmylonas.petstore_api_app.models.ResponseBuilder;
import com.thomasmylonas.petstore_api_app.models.ResponseSuccess;
import com.thomasmylonas.petstore_api_app.services.PetService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
//@AutoConfigureMockMvc
@WebMvcTest(controllers = {PetController.class})
//@ContextConfiguration(classes = PetstoreApiAppApplication.class)
@ActiveProfiles(profiles = {"test"})
@Slf4j
public class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PetService mockPetService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ResponseBuilder responseBuilder;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void findPetById() throws Exception {

        final long PET_ID = 1;
        PetResponseDto petResponseDto = PetResponseDto.builder()
                .id(PET_ID)
                .name("Pet_Name")
                .status(PetStatus.AVAILABLE.getValue())
                .categoryResponseDto(CategoryResponseDto.builder()
                        .id(1)
                        .name("Pet_Category")
                        .build())
                .tagResponseDtos(List.of(
                                TagResponseDto.builder()
                                        .id(1L)
                                        .name("Pet_Tag_1")
                                        .build()
                        )
                )
                .photoUrlResponseDtos(
                        List.of(
                                PhotoUrlResponseDto.builder()
                                        .id(1L)
                                        .name("Tag_Photo_Url")
                                        .build()
                        )
                )
                .build();

        String petResponseDtoJson = objectMapper.writeValueAsString(petResponseDto);
        Map<String, PetResponseDto> petResponseMap = Map.of("pet_response", petResponseDto);
        final ResponseSuccess RESPONSE_SUCCESS = ResponseSuccess.builder()
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.toString())
                .message("Hello")
                .path("")
                .data(petResponseMap)
                .build();

        String petResponseMapJson = objectMapper.writeValueAsString(petResponseMap);
        String RESPONSE_SUCCESS_JSON = objectMapper.writeValueAsString(RESPONSE_SUCCESS);

        when(mockPetService.findPetById(any())).thenReturn(petResponseDto);
        when(responseBuilder.buildResponseSuccess(HttpStatus.OK, "Success: The Pet with ID " + PET_ID + " is found!", Map.of("pet_response", petResponseDto)))
                .thenReturn(ResponseEntity.ok().body(RESPONSE_SUCCESS));

        mockMvc.perform(get("/api/v1/pets/" + PET_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.data.pet_response.name").value(petResponseDto.name()))
                .andExpect(jsonPath("$.data.pet_response.status").value(petResponseDto.status()))
                .andExpect(jsonPath("$.data.pet_response.category.name").value(petResponseDto.categoryResponseDto().name()))
                .andExpect(jsonPath("$.data.pet_response.tags[0].name").value(petResponseDto.tagResponseDtos().getFirst().name()))
                .andExpect(jsonPath("$.data.pet_response.photo_urls[0].name").value(petResponseDto.photoUrlResponseDtos().getFirst().name()))
        ;

        log.info("petResponseDtoJson: {}", petResponseDtoJson);
        log.info("petResponseMapJson: {}", petResponseMapJson);
    }

    @Test
    public void findPetsByName() {
    }

    @Test
    public void findPetsByStatus() {
    }

    @Test
    public void findAllPets() {
    }

    @Test
    public void findAllPetsSorted() {
    }

    @Test
    public void savePet() {
    }

    @Test
    public void saveAllPets() {
    }

    @Test
    public void updatePet() {
    }

    @Test
    public void deletePetById() {
    }

    @Test
    public void updatePetWithForm() {
    }
}
