package com.thomasmylonas.petstore_api_app.services;

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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    @DisplayName(value = "Given: PetIdToUpdate, When: updatePet is called, Then: RequestedResourceNotFoundException is thrown")
    public void test_Given_PetIdToUpdate_When_UpdatePetIsCalled_Then_RequestedResourceNotFoundExceptionIsThrown() {

        // Given / Arrange

        final Long PET_ID_TO_UPDATE = 1L;

        when(mockPetRepository.findById(PET_ID_TO_UPDATE)).thenThrow(RequestedResourceNotFoundException.class);
        //doThrow(IllegalArgumentException.class).when(mockPetRepository).save(null); // Will never happen, because of the "RequestedResourceNotFoundException"

        // When / Act - Then / Assert

        verify(mockPetRepository, never()).save(any());
        assertThrows(RequestedResourceNotFoundException.class, () -> petService.updatePet(PET_ID_TO_UPDATE, any()));
        //assertThrows(IllegalArgumentException.class, () -> petService.updatePet(PET_ID_TO_UPDATE, any())); // Will never happen, because of the "RequestedResourceNotFoundException"
    }

    @Test
    @DisplayName(value = "Given: PetId, When: deletePetById is called, Then: IllegalArgumentException is thrown")
    public void test_Given_PetId_When_DeletePetByIdIsCalled_Then_IllegalArgumentExceptionIsThrown() {

        // Given / Arrange

        final Long PET_ID = 1L;
        doThrow(IllegalArgumentException.class).when(mockPetRepository).deleteById(PET_ID);

        // When / Act - Then / Assert

        assertThrows(IllegalArgumentException.class, () -> petService.deletePetById(PET_ID));
    }

    @Test
    @DisplayName(value = "Given: PetIdToUpdate and PetRequestDto, When: updatePetWithForm is called, Then: RequestedResourceNotFoundException is thrown")
    public void test_Given_PetIdToUpdate_And_PetRequestDto_When_UpdatePetWithFormIsCalled_Then_RequestedResourceNotFoundExceptionIsThrown() {

        // Given / Arrange

        // When / Act

        // Then / Assert
    }
}
