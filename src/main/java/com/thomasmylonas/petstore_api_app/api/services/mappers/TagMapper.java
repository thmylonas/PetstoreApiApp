package com.thomasmylonas.petstore_api_app.api.services.mappers;

import com.thomasmylonas.petstore_api_app.api.dtos.tag_dtos.TagRequestDto;
import com.thomasmylonas.petstore_api_app.api.dtos.tag_dtos.TagResponseDto;
import com.thomasmylonas.petstore_api_app.api.entities.Tag;
import org.springframework.stereotype.Service;

@Service
public class TagMapper {

    public Tag toTag(TagRequestDto tagRequestDto) {
        return Tag.builder()
                .name(tagRequestDto.name())
                .build();
    }

    public TagResponseDto fromTag(Tag tag) {
        return TagResponseDto.builder()
                .id(tag.getId())
                .name(tag.getName())
                .build();
    }
}
