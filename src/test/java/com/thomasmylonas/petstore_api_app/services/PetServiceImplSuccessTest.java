package com.thomasmylonas.petstore_api_app.services;

import com.thomasmylonas.petstore_api_app.dtos.pet_dtos.PetResponseDto;
import com.thomasmylonas.petstore_api_app.entities.Pet;
import com.thomasmylonas.petstore_api_app.enums.PetStatus;
import com.thomasmylonas.petstore_api_app.helpers.HelperClass;
import com.thomasmylonas.petstore_api_app.helpers.TestDataProvider;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(value = MockitoExtension.class)
@Slf4j
public class PetServiceImplSuccessTest {

    public final List<Long> RANDOM_IDS = HelperClass.randomLongNumbers(10);

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
    @DisplayName(value = "Given: Pets, When: findAllPets is called, Then: allPets are returned")
    public void test_Given_Pets_When_FindAllPetsIsCalled_Then_AllPetsAreReturned() {

        // Given / Arrange

        final List<PetResponseDto> PET_RESPONSE_DTOS = TestDataProvider.PET_REQUEST_DTOS.stream()
                .map(petRequestDto -> PetResponseDto.builder()
                        .id(RANDOM_IDS.get(HelperClass.RANDOM.nextInt(RANDOM_IDS.size())))
                        .name(petRequestDto.name())
                        .status(petRequestDto.status())
                        .build()
                ).toList();
        final List<Pet> PETS = TestDataProvider.PET_REQUEST_DTOS.stream()
                .map(petRequestDto -> Pet.builder()
                        .id(RANDOM_IDS.get(HelperClass.RANDOM.nextInt(RANDOM_IDS.size())))
                        .name(petRequestDto.name())
                        .status(PetStatus.valueOfPetStatus(petRequestDto.status()))
                        .build()
                ).toList();

        when(mockPetRepository.findAll()).thenReturn(PETS);
        for (int i = 0; i < PETS.size(); i++) {
            when(mockPetMapper.fromPet(PETS.get(i))).thenReturn(PET_RESPONSE_DTOS.get(i));
        }

        // When / Act

        List<PetResponseDto> allPets = petService.findAllPets();
        log.info("AllPets: {}", allPets);

        // Then / Assert

        assertEquals(PET_RESPONSE_DTOS, allPets);
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
