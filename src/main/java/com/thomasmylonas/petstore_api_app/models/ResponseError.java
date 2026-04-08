package com.thomasmylonas.petstore_api_app.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResponseError(
        LocalDateTime timestamp,
        String statusCode,
        String message,
        String path,
        Map<String, ?> data
        //,String stacktrace // Optional
) {
}
