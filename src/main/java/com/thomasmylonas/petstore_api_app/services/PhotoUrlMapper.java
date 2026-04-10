package com.thomasmylonas.petstore_api_app.services;

import com.thomasmylonas.petstore_api_app.dtos.photo_url_dtos.PhotoUrlRequestDto;
import com.thomasmylonas.petstore_api_app.dtos.photo_url_dtos.PhotoUrlResponseDto;
import com.thomasmylonas.petstore_api_app.entities.PhotoUrl;
import org.springframework.stereotype.Service;

@Service
public class PhotoUrlMapper {

    public PhotoUrlResponseDto fromPhotoUrl(PhotoUrl photoUrl) {
        return PhotoUrlResponseDto.builder()
                .id(photoUrl.getId())
                .name(photoUrl.getName())
                .build();
    }

    public PhotoUrl toPhotoUrl(PhotoUrlRequestDto photoUrlRequestDto) {
        return PhotoUrl.builder()
                .name(photoUrlRequestDto.name())
                .build();
    }
}
