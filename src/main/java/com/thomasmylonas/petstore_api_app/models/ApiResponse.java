package com.thomasmylonas.petstore_api_app.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ApiResponse {
    private int code;
    private String type;
    private String message;
}
