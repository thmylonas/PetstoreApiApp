package com.thomasmylonas.petstore_api_app.services.mappers;

import com.thomasmylonas.petstore_api_app.api.dtos.photo_url_dtos.PhotoUrlRequestDto;
import com.thomasmylonas.petstore_api_app.api.dtos.photo_url_dtos.PhotoUrlResponseDto;
import com.thomasmylonas.petstore_api_app.entities.PhotoUrl;
import org.springframework.stereotype.Service;

@Service
public class PhotoUrlMapper {

    public PhotoUrl toPhotoUrl(PhotoUrlRequestDto photoUrlRequestDto) {
        return PhotoUrl.builder()
                .name(photoUrlRequestDto.name())
                .build();
    }

    public PhotoUrlResponseDto fromPhotoUrl(PhotoUrl photoUrl) {
        return PhotoUrlResponseDto.builder()
                .id(photoUrl.getId())
                .name(photoUrl.getName())
                .build();
    }
}
