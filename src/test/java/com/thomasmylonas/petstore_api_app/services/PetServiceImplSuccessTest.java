package com.thomasmylonas.petstore_api_app.services;

import com.thomasmylonas.petstore_api_app.api.dtos.category_dtos.CategoryRequestDto;
import com.thomasmylonas.petstore_api_app.api.dtos.category_dtos.CategoryResponseDto;
import com.thomasmylonas.petstore_api_app.api.dtos.pet_dtos.PetRequestDto;
import com.thomasmylonas.petstore_api_app.api.dtos.pet_dtos.PetResponseDto;
import com.thomasmylonas.petstore_api_app.api.entities.Category;
import com.thomasmylonas.petstore_api_app.api.entities.Pet;
import com.thomasmylonas.petstore_api_app.api.enums.PetStatus;
import com.thomasmylonas.petstore_api_app.helpers.HelperClass;
import com.thomasmylonas.petstore_api_app.helpers.TestDataProvider;
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
import org.springframework.data.domain.Sort;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(value = MockitoExtension.class)
@Slf4j
public class PetServiceImplSuccessTest {

    public final List<Long> RANDOM_IDS = HelperClass.randomLongNumbers(10);

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
    @DisplayName(value = "Given: PetName, When: findPetsByName is called, Then: petResponseDtos are returned")
    public void test_Given_PetName_When_FindPetsByNameIsCalled_Then_PetResponseDtosAreReturned() {

        // Given / Arrange

        final Long PET_ID = 1L;
        final String PET_NAME = "tom";
        final List<Pet> PETS = List.of(Pet.builder()
                .id(PET_ID)
                .name(PET_NAME)
                .status(PetStatus.AVAILABLE)
                .build());
        final List<PetResponseDto> PET_RESPONSE_DTOS = PETS.stream()
                .map(pet -> PetResponseDto.builder()
                        .id(pet.getId())
                        .name(pet.getName())
                        .status(pet.getStatus().getValue())
                        .build()
                ).toList();
        when(mockPetRepository.findByName(PET_NAME)).thenReturn(Optional.of(PETS));
        for (int i = 0; i < PETS.size(); i++) {
            when(mockPetMapper.fromPet(PETS.get(i))).thenReturn(PET_RESPONSE_DTOS.get(i));
        }

        // When / Act

        List<PetResponseDto> petResponseDtos = petService.findPetsByName(PET_NAME);
        log.info("PetResponseDtos: {}", petResponseDtos);

        // Then / Assert

        assertEquals(PET_RESPONSE_DTOS, petResponseDtos);
    }

    @Test
    @DisplayName(value = "Given: PetStatus, When: findPetsByStatus is called, Then: petResponseDtos are returned")
    public void test_Given_PetStatus_When_FindPetsByStatusIsCalled_Then_PetResponseDtosAreReturned() {

        // Given / Arrange

        final String PET_STATUS = PetStatus.AVAILABLE.getValue();
        final List<Pet> PETS = TestDataProvider.PET_REQUEST_DTOS.stream()
                .filter(petRequestDto -> petRequestDto.status().equalsIgnoreCase(PET_STATUS.toLowerCase()))
                .map(petRequestDto -> Pet.builder()
                        .id(RANDOM_IDS.get(HelperClass.RANDOM.nextInt(RANDOM_IDS.size())))
                        .name(petRequestDto.name())
                        .status(PetStatus.valueOfPetStatus(petRequestDto.status()))
                        .build())
                .toList();
        final List<PetResponseDto> PET_RESPONSE_DTOS = TestDataProvider.PET_REQUEST_DTOS.stream()
                .filter(petRequestDto -> petRequestDto.status().equalsIgnoreCase(PET_STATUS.toLowerCase()))
                .map(petRequestDto -> PetResponseDto.builder()
                        .id(RANDOM_IDS.get(HelperClass.RANDOM.nextInt(RANDOM_IDS.size())))
                        .name(petRequestDto.name())
                        .status(petRequestDto.status())
                        .build())
                .toList();
        for (String s : PET_STATUS.split(",")) {
            when(mockPetRepository.findByStatus(PetStatus.valueOfPetStatus(s))).thenReturn(Optional.of(PETS));
        }
        for (int i = 0; i < PETS.size(); i++) {
            when(mockPetMapper.fromPet(PETS.get(i))).thenReturn(PET_RESPONSE_DTOS.get(i));
        }

        // When / Act

        List<PetResponseDto> petResponseDtos = petService.findPetsByStatus(PET_STATUS);
        log.info("PetResponseDtos: {}", petResponseDtos);

        // Then / Assert

        assertEquals(PET_RESPONSE_DTOS, petResponseDtos);
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
    @DisplayName(value = "Given: Pets, When: findAllPetsSorted is called, Then: allPets are returned")
    public void test_Given_Pets_When_FindAllPetsSortedIsCalled_Then_AllPetsAreReturned() {

        // Given / Arrange

        final String SORT_BY = "name";
        final String SORT_DIRECTION = "asc";
        final Sort SORT = Sort.by(SORT_BY).ascending();

        final List<PetResponseDto> PET_RESPONSE_DTOS = TestDataProvider.PET_REQUEST_DTOS.stream()
                .map(petRequestDto -> PetResponseDto.builder()
                        .id(RANDOM_IDS.get(HelperClass.RANDOM.nextInt(RANDOM_IDS.size())))
                        .name(petRequestDto.name())
                        .status(petRequestDto.status())
                        .build()
                )
                .sorted(Comparator.comparing(PetResponseDto::name))
                .toList();
        final List<Pet> PETS = TestDataProvider.PET_REQUEST_DTOS.stream()
                .map(petRequestDto -> Pet.builder()
                        .id(RANDOM_IDS.get(HelperClass.RANDOM.nextInt(RANDOM_IDS.size())))
                        .name(petRequestDto.name())
                        .status(PetStatus.valueOfPetStatus(petRequestDto.status()))
                        .build()
                )
                .sorted(Comparator.comparing(Pet::getName))
                .toList();

        when(mockPetRepository.findAll(SORT)).thenReturn(PETS);
        for (int i = 0; i < PETS.size(); i++) {
            when(mockPetMapper.fromPet(PETS.get(i))).thenReturn(PET_RESPONSE_DTOS.get(i));
        }

        // When / Act

        List<PetResponseDto> allPets = petService.findAllPetsSorted(SORT_BY, SORT_DIRECTION);
        log.info("AllPets: {}", allPets);

        // Then / Assert

        assertEquals(PET_RESPONSE_DTOS, allPets);
    }

    @Test
    @DisplayName(value = "Given: PetRequestDto, When: savePet is called, Then: Verify that savePet is called once")
    public void test_Given_PetRequestDto_When_SavePetIsCalled_Then_VerifyIsCalledOnce() {

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
        final PetResponseDto PET_RESPONSE_DTO = PetResponseDto.builder()
                .id(PET.getId())
                .name(PET.getName())
                .status(PET.getStatus().getValue())
                .categoryResponseDto(CategoryResponseDto.builder()
                        .name(PET.getCategory().getName())
                        .build())
                .build();

        when(mockCategoryRepository.findByName(PET.getCategory().getName())).thenReturn(Optional.of(List.of(PET.getCategory())));
        when(mockPetMapper.toPet(PET_REQUEST_DTO)).thenReturn(PET);
        when(mockCategoryMapper.toCategory(PET_REQUEST_DTO.categoryRequestDto())).thenReturn(PET.getCategory());
        when(mockPetMapper.fromPet(PET)).thenReturn(PET_RESPONSE_DTO);
        when(mockPetRepository.save(PET)).thenReturn(PET);
        //when(mockCategoryRepository.save(PET.getCategory())).thenReturn(PET.getCategory()); // Will never happen, because "!List.of(PET.getCategory()).isEmpty()"

        // When / Act

        PetResponseDto petResponseDto = petService.savePet(PET_REQUEST_DTO);
        log.info("petResponseDto: {}", petResponseDto);

        // Then / Assert

        //verify(mockCategoryRepository, times(1)).save(PET.getCategory()); // Will never happen, because "!List.of(PET.getCategory()).isEmpty()"
        verify(mockPetRepository, times(1)).save(PET);
        assertEquals(PET_RESPONSE_DTO, petResponseDto);
    }

    @Test
    @DisplayName(value = "Given: PetRequestDto and PetIdToUpdate, When: updatePet is called, Then: petResponseDto is returned")
    public void test_Given_PetRequestDto_And_PetIdToUpdate_When_UpdatePetIsCalled_Then_PetResponseDtoIsReturned() {

        // Given / Arrange

        final Long PET_ID_TO_UPDATE = 1L;
        final PetRequestDto PET_REQUEST_DTO = PetRequestDto.builder()
                .name("New_Pet_Name")
                .status(PetStatus.PENDING.getValue())
                .categoryRequestDto(CategoryRequestDto.builder()
                        .name("New_Pet_Category")
                        .build())
                .build();
        final Pet PET = Pet.builder()
                .name(PET_REQUEST_DTO.name())
                .status(PetStatus.valueOfPetStatus(PET_REQUEST_DTO.status()))
                .category(Category.builder()
                        .name(PET_REQUEST_DTO.categoryRequestDto().name())
                        .build())
                .build();
        final Pet PET_TO_UPDATE = Pet.builder()
                .id(PET_ID_TO_UPDATE)
                .name("Pet_Name")
                .status(PetStatus.AVAILABLE)
                .category(Category.builder()
                        .name("Pet_Category")
                        .build())
                .build();
        final Pet PET_UPDATED = Pet.builder()
                .id(PET_ID_TO_UPDATE)
                .name(PET_REQUEST_DTO.name())
                .status(PetStatus.valueOfPetStatus(PET_REQUEST_DTO.status()))
                .category(Category.builder()
                        .name(PET_REQUEST_DTO.categoryRequestDto().name())
                        .build())
                .build();
        final PetResponseDto PET_RESPONSE_DTO = PetResponseDto.builder()
                .id(PET_ID_TO_UPDATE)
                .name(PET_REQUEST_DTO.name())
                .status(PET_REQUEST_DTO.status())
                .categoryResponseDto(CategoryResponseDto.builder()
                        .name(PET_REQUEST_DTO.categoryRequestDto().name())
                        .build())
                .build();

        when(mockPetRepository.findById(PET_ID_TO_UPDATE)).thenReturn(Optional.of(PET_TO_UPDATE));
        when(mockPetMapper.toPet(PET_REQUEST_DTO)).thenReturn(PET);
        when(mockPetRepository.save(PET_TO_UPDATE)).thenReturn(PET_UPDATED);
        when(mockPetMapper.fromPet(PET_UPDATED)).thenReturn(PET_RESPONSE_DTO);

        // When / Act

        PetResponseDto petResponseDto = petService.updatePet(PET_REQUEST_DTO, PET_ID_TO_UPDATE);
        log.info("petResponseDto: {}", petResponseDto);

        // Then / Assert

        assertEquals(PET_RESPONSE_DTO, petResponseDto);
    }

    @Test
    @DisplayName(value = "Given: PetId, When: deletePetById is called, Then: PetService::deletePetById is called once")
    public void test_Given_PetId_When_DeletePetByIdIsCalled_Then_PetServiceDeletePetByIdIsCalledOnce() {

        // Given / Arrange

        final Long PET_ID = 1L;
        doNothing().when(mockPetRepository).deleteById(PET_ID);

        // When / Act

        petService.deletePetById(PET_ID);

        // Then / Assert

        verify(mockPetRepository, times(1)).deleteById(PET_ID);
    }

    @Test
    @DisplayName(value = "Given: PetIdToUpdate and PetName and PetStatus, When: updatePetWithForm is called, Then: petResponseDto is returned")
    public void test_Given_PetIdToUpdate_And_PetName_And_PetStatus_When_UpdatePetWithFormIsCalled_Then_PetResponseDtoIsReturned() {

        // Given / Arrange

        final Long PET_ID_TO_UPDATE = 1L;
        final String PET_NAME = "New_Pet_Name";
        final String PET_STATUS = PetStatus.PENDING.getValue();

        final Pet PET_TO_UPDATE = Pet.builder()
                .id(PET_ID_TO_UPDATE)
                .name("Pet_Name")
                .status(PetStatus.AVAILABLE)
                .build();
        final Pet PET_UPDATED = Pet.builder()
                .id(PET_ID_TO_UPDATE)
                .name(PET_NAME)
                .status(PetStatus.valueOfPetStatus(PET_STATUS))
                .build();
        final PetResponseDto PET_RESPONSE_DTO = PetResponseDto.builder()
                .id(PET_ID_TO_UPDATE)
                .name(PET_NAME)
                .status(PET_STATUS)
                .build();

        when(mockPetRepository.findById(PET_ID_TO_UPDATE)).thenReturn(Optional.of(PET_TO_UPDATE));
        when(mockPetRepository.save(PET_TO_UPDATE)).thenReturn(PET_UPDATED);
        when(mockPetMapper.fromPet(PET_UPDATED)).thenReturn(PET_RESPONSE_DTO);

        // When / Act

        PetResponseDto petResponseDto = petService.updatePetWithForm(PET_ID_TO_UPDATE, PET_NAME, PET_STATUS);
        log.info("petResponseDto: {}", petResponseDto);

        // Then / Assert

        assertEquals(PET_RESPONSE_DTO, petResponseDto);
    }
}
