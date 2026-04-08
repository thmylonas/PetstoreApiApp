package com.thomasmylonas.petstore_api_app.models;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class ResponseBuilder {

    public ResponseEntity<ResponseSuccess> buildResponse(HttpStatus httpStatus, String message, Map<String, ?> data) {
        return buildResponse(httpStatus, message, ServletUriComponentsBuilder.fromCurrentRequest().toUriString(), data);
    }

    public ResponseEntity<ResponseSuccess> buildResponse(HttpStatus httpStatus, String message, String path, Map<String, ?> data) {

        ResponseSuccess responseSuccess = ResponseSuccess.builder()
                .timestamp(LocalDateTime.now())
                .statusCode(httpStatus.toString())
                .message(message)
                .path(path)
                .data(data)
                .build();
        return ResponseEntity.status(httpStatus).header("Location", path).body(responseSuccess);
    }
}
