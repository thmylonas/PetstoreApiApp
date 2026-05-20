package com.thomasmylonas.petstore_api_app.services;

import com.thomasmylonas.petstore_api_app.dtos.pet_dtos.PetResponseDto;
import com.thomasmylonas.petstore_api_app.entities.Pet;
import com.thomasmylonas.petstore_api_app.enums.PetStatus;
import com.thomasmylonas.petstore_api_app.repositories.PetRepository;
import com.thomasmylonas.petstore_api_app.services.mappers.PetMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(value = MockitoExtension.class)
@Slf4j
public class PetServiceImplSuccessTest {

    @Mock
    private PetRepository mockPetRepository;

    @Mock
    private PetMapper mockPetMapper;

    @InjectMocks
    private PetServiceImpl petService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName(value = "Given: PetId, When: findPetById is called, Then: petById is returned")
    public void test_Given_PetId_When_FindPetByIdIsCalled_Then_PetByIdIsReturned() {

        // Given / Arrange

        final Long PET_ID = 1L;
        final Pet PET = Pet.builder()
                .id(PET_ID)
                .name("Pet_Name")
                .status(PetStatus.AVAILABLE)
                .build();
        final PetResponseDto PET_RESPONSE_DTO = PetResponseDto.builder()
                .id(PET.getId())
                .name(PET.getName())
                .status(PET.getStatus().getValue())
                .build();

        when(mockPetRepository.findById(PET_ID)).thenReturn(Optional.of(PET));
        when(mockPetMapper.fromPet(PET)).thenReturn(PET_RESPONSE_DTO);

        // When / Act

        PetResponseDto petById = petService.findPetById(PET_ID);
        log.info("PetById: {}", petById);

        // Then / Assert

        assertEquals(PET_RESPONSE_DTO, petById);
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
