package com.thomasmylonas.petstore_api_app.services;

import com.thomasmylonas.petstore_api_app.dtos.category_dtos.CategoryRequestDto;
import com.thomasmylonas.petstore_api_app.dtos.pet_dtos.PetRequestDto;
import com.thomasmylonas.petstore_api_app.entities.Category;
import com.thomasmylonas.petstore_api_app.entities.Pet;
import com.thomasmylonas.petstore_api_app.enums.PetStatus;
import com.thomasmylonas.petstore_api_app.exceptions.RequestedResourceNotFoundException;
import com.thomasmylonas.petstore_api_app.repositories.CategoryRepository;
import com.thomasmylonas.petstore_api_app.repositories.PetRepository;
import com.thomasmylonas.petstore_api_app.services.mappers.CategoryMapper;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(value = MockitoExtension.class)
@Slf4j
public class PetServiceImplFailureTest {

    @Mock
    private PetRepository mockPetRepository;

    @Mock
    private CategoryRepository mockCategoryRepository;

    @Mock
    private PetMapper mockPetMapper;

    @Mock
    private CategoryMapper mockCategoryMapper;

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

        // When / Act

        // Then / Assert
    }

    @Test
    @DisplayName(value = "Given: PetName, When: findPetsByName is called, Then: petResponseDtos are returned")
    public void test_Given_PetName_When_FindPetsByNameIsCalled_Then_PetResponseDtosAreReturned() {

        // Given / Arrange

        // When / Act

        // Then / Assert
    }

    @Test
    @DisplayName(value = "Given: PetStatus, When: findPetsByStatus is called, Then: petResponseDtos are returned")
    public void test_Given_PetStatus_When_FindPetsByStatusIsCalled_Then_PetResponseDtosAreReturned() {

        // Given / Arrange

        // When / Act

        // Then / Assert
    }

    @Test
    @DisplayName(value = "Given: Pets, When: findAllPets is called, Then: allPets are returned")
    public void test_Given_Pets_When_FindAllPetsIsCalled_Then_AllPetsAreReturned() {

        // Given / Arrange

        // When / Act

        // Then / Assert
    }

    @Test
    @DisplayName(value = "Given: Pets, When: findAllPetsSorted is called, Then: allPets are returned")
    public void test_Given_Pets_When_FindAllPetsSortedIsCalled_Then_AllPetsAreReturned() {

        // Given / Arrange

        // When / Act

        // Then / Assert
    }

    @Test
    @DisplayName(value = "Given: Pet, When: savePet is called, Then: Verify that savePet is called once")
    public void test_Given_Pet_When_SavePetIsCalled_Then_VerifyIsCalledOnce() {

        // Given / Arrange

        // When / Act

        // Then / Assert
    }

    @Test
    @DisplayName(value = "Given: PetIdToUpdate and PetRequestDto, When: updatePet is called, Then: RequestedResourceNotFoundException is thrown")
    public void test_Given_PetIdToUpdate_And_PetRequestDto_When_UpdatePetIsCalled_Then_RequestedResourceNotFoundExceptionIsThrown() {

        // Given / Arrange

        final Long PET_ID = 1L;
        final Pet PET = Pet.builder()
                .id(PET_ID)
                .name("Pet_Name")
                .status(PetStatus.AVAILABLE)
                .category(Category.builder()
                        .name("Pet_Category")
                        .build())
                .build();
        final PetRequestDto PET_REQUEST_DTO = PetRequestDto.builder()
                .name("New_Pet_Name")
                .status(PetStatus.PENDING.getValue())
                .categoryRequestDto(CategoryRequestDto.builder()
                        .name("New_Pet_Category")
                        .build())
                .build();
        when(mockPetRepository.findById(PET.getId())).thenThrow(RequestedResourceNotFoundException.class);
        //doThrow(IllegalArgumentException.class).when(mockPetRepository).save(null); // Will never happen, because of the "RequestedResourceNotFoundException"

        // When / Act - Then / Assert

        verify(mockPetRepository, never()).save(PET);
        assertThrows(RequestedResourceNotFoundException.class, () -> petService.updatePet(PET_ID, PET_REQUEST_DTO));
        //assertThrows(IllegalArgumentException.class, () -> petService.updatePet(PET_ID, PET_REQUEST_DTO)); // Will never happen, because of the "RequestedResourceNotFoundException"
    }

    @Test
    @DisplayName(value = "Given: PetId, When: deletePetById is called, Then: PetService::deletePetById is called once")
    public void test_Given_PetId_When_DeletePetByIdIsCalled_Then_PetServiceDeletePetByIdIsCalledOnce() {

        // Given / Arrange

        // When / Act

        // Then / Assert
    }

    @Test
    @DisplayName(value = "Given: PetIdToUpdate and PetRequestDto, When: updatePetWithForm is called, Then: RequestedResourceNotFoundException is thrown")
    public void test_Given_PetIdToUpdate_And_PetRequestDto_When_UpdatePetWithFormIsCalled_Then_RequestedResourceNotFoundExceptionIsThrown() {

        // Given / Arrange

        // When / Act

        // Then / Assert
    }
}
