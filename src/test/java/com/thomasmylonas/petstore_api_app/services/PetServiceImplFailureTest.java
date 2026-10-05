package com.thomasmylonas.petstore_api_app.services;

import com.thomasmylonas.petstore_api_app.api.dtos.category_dtos.CategoryRequestDto;
import com.thomasmylonas.petstore_api_app.api.dtos.pet_dtos.PetRequestDto;
import com.thomasmylonas.petstore_api_app.api.entities.Category;
import com.thomasmylonas.petstore_api_app.api.entities.Pet;
import com.thomasmylonas.petstore_api_app.api.enums.PetStatus;
import com.thomasmylonas.petstore_api_app.api.exceptions.RequestedResourceNotFoundException;
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

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

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
    @DisplayName(value = "When: findPetById is called, Then: RequestedResourceNotFoundException is thrown")
    public void test_When_FindPetByIdIsCalled_Then_RequestedResourceNotFoundExceptionIsThrown() {

        // Given / Arrange

        when(mockPetRepository.findById(any())).thenThrow(RequestedResourceNotFoundException.class);

        // When / Act - Then / Assert

        assertThrows(RequestedResourceNotFoundException.class, () -> petService.findPetById(any()));
    }

    @Test
    @DisplayName(value = "When: findPetsByName is called, Then: RequestedResourceNotFoundException is thrown")
    public void test_When_FindPetsByNameIsCalled_Then_RequestedResourceNotFoundExceptionIsThrown() {

        // Given / Arrange

        when(mockPetRepository.findByName(any())).thenThrow(RequestedResourceNotFoundException.class);

        // When / Act - Then / Assert

        assertThrows(RequestedResourceNotFoundException.class, () -> petService.findPetsByName(any()));
    }

    @Test
    @DisplayName(value = "Given: PetStatus, When: findPetsByStatus is called, Then: RequestedResourceNotFoundException is thrown")
    public void test_Given_PetStatus_When_FindPetsByStatusIsCalled_Then_RequestedResourceNotFoundExceptionIsThrown() {

        // Given / Arrange

        final String PET_STATUS = PetStatus.AVAILABLE.getValue();

        doThrow(RequestedResourceNotFoundException.class).when(mockPetRepository).findByStatus(any());

        // When / Act - Then / Assert

        assertThrows(RequestedResourceNotFoundException.class, () -> petService.findPetsByStatus(PET_STATUS));
    }

    @Test
    @DisplayName(value = "Given: PetRequestDto, When: savePet is called, Then: RequestedResourceNotFoundException is thrown")
    public void test_Given_PetRequestDto_When_SavePetIsCalled_Then_RequestedResourceNotFoundExceptionIsThrown() {

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
                .name(PET.getName())
                .status(PET.getStatus().getValue())
                .categoryRequestDto(CategoryRequestDto.builder()
                        .name(PET.getCategory().getName())
                        .build())
                .build();

        when(mockPetMapper.toPet(any())).thenReturn(PET);
        when(mockCategoryMapper.toCategory(any())).thenReturn(PET.getCategory());
        when(mockCategoryRepository.findByName(any())).thenThrow(RequestedResourceNotFoundException.class);

        // When / Act -  Then / Assert

        assertThrows(RequestedResourceNotFoundException.class, () -> petService.savePet(PET_REQUEST_DTO));
    }

    @Test
    @DisplayName(value = "When: savePet is called, Then: NullPointerException is thrown")
    public void test_When_SavePetIsCalled_Then_NullPointerExceptionIsThrown() {

        // Given / Arrange

        when(mockPetMapper.toPet(any())).thenReturn(null);

        // When / Act -  Then / Assert

        assertThrows(NullPointerException.class, () -> petService.savePet(null));
    }

    @Test
    @DisplayName(value = "Given: PetIdToUpdate, When: updatePet is called, Then: RequestedResourceNotFoundException is thrown")
    public void test_Given_PetIdToUpdate_When_UpdatePetIsCalled_Then_RequestedResourceNotFoundExceptionIsThrown() {

        // Given / Arrange

        final Long PET_ID_TO_UPDATE = 1L;

        when(mockPetRepository.findById(PET_ID_TO_UPDATE)).thenThrow(RequestedResourceNotFoundException.class);
        //doThrow(IllegalArgumentException.class).when(mockPetRepository).save(null); // Will never happen, because of the "RequestedResourceNotFoundException"

        // When / Act - Then / Assert

        assertThrows(RequestedResourceNotFoundException.class, () -> petService.updatePet(any(), PET_ID_TO_UPDATE));
        //assertThrows(IllegalArgumentException.class, () -> petService.updatePet(any(), PET_ID_TO_UPDATE)); // Will never happen, because of the "RequestedResourceNotFoundException"
        verify(mockPetRepository, never()).save(any());
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
    @DisplayName(value = "Given: PetIdToUpdate and PetName and PetStatus, When: updatePetWithForm is called, Then: RequestedResourceNotFoundException is thrown")
    public void test_Given_PetIdToUpdate_And_PetName_And_PetStatus_When_UpdatePetWithFormIsCalled_Then_RequestedResourceNotFoundExceptionIsThrown() {

        // Given / Arrange

        final Long PET_ID_TO_UPDATE = 1L;
        final String PET_NAME = "New_Pet_Name";
        final String PET_STATUS = PetStatus.PENDING.getValue();

        when(mockPetRepository.findById(PET_ID_TO_UPDATE)).thenThrow(RequestedResourceNotFoundException.class);
        //doThrow(IllegalArgumentException.class).when(mockPetRepository).save(null); // Will never happen, because of the "RequestedResourceNotFoundException"

        // When / Act - Then / Assert

        assertThrows(RequestedResourceNotFoundException.class, () -> petService.updatePetWithForm(PET_ID_TO_UPDATE, PET_NAME, PET_STATUS));
        //assertThrows(IllegalArgumentException.class, () -> petService.updatePetWithForm(PET_ID_TO_UPDATE, PET_NAME, PET_STATUS)); // Will never happen, because of the "RequestedResourceNotFoundException"
        verify(mockPetRepository, never()).save(any());
    }
}
