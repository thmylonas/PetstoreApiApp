package com.thomasmylonas.petstore_api_web_app.service_layer.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ApiResponse {
    private int code;
    private String type;
    private String message;
}
