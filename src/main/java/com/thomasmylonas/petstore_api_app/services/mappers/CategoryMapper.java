package com.thomasmylonas.petstore_api_app.services.mappers;

import com.thomasmylonas.petstore_api_app.dtos.category_dtos.CategoryRequestDto;
import com.thomasmylonas.petstore_api_app.dtos.category_dtos.CategoryResponseDto;
import com.thomasmylonas.petstore_api_app.entities.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryMapper {

    public CategoryResponseDto fromCategory(Category category) {
        return CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public Category toCategory(CategoryRequestDto categoryRequestDto) {
        return Category.builder()
                .name(categoryRequestDto.name())
                .pets(new ArrayList<>())
                .build();
    }
}
