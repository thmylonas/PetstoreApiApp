package com.thomasmylonas.petstore_api_app.services;

import com.thomasmylonas.petstore_api_app.dtos.tag_dtos.TagRequestDto;
import com.thomasmylonas.petstore_api_app.dtos.tag_dtos.TagResponseDto;
import com.thomasmylonas.petstore_api_app.entities.Tag;
import org.springframework.stereotype.Service;

@Service
public class TagMapper {

    public TagResponseDto fromTag(Tag tag) {
        return TagResponseDto.builder()
                .id(tag.getId())
                .name(tag.getName())
                .build();
    }

    public Tag toTag(TagRequestDto tagRequestDto) {
        return Tag.builder()
                .name(tagRequestDto.name())
                .build();
    }
}
