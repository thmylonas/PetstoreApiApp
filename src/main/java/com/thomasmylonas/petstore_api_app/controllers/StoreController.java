package com.thomasmylonas.petstore_api_app.controllers;

import com.thomasmylonas.petstore_api_app.dtos.order_dtos.OrderRequestDto;
import com.thomasmylonas.petstore_api_app.dtos.order_dtos.OrderResponseDto;
import com.thomasmylonas.petstore_api_app.services.OrderService;
import com.thomasmylonas.petstore_api_app.models.ResponseBuilder;
import com.thomasmylonas.petstore_api_app.models.ResponseSuccess;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/store")
@RequiredArgsConstructor
@Slf4j
public class StoreController {

    private static final String REQUEST_MAPPING = "/api/v1/store";

    private final OrderService orderService;
    private final ResponseBuilder responseBuilder;

    @PostMapping(path = {"/order"})
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseSuccess> saveOrder(@RequestBody OrderRequestDto orderRequestDto) {

        final String message = "Created: The Order has been created successfully!";
        OrderResponseDto orderResponseDto = orderService.saveOrder(orderRequestDto);
        String savedOrderUri = ServletUriComponentsBuilder
                .fromCurrentContextPath() // "http://localhost:8080"
                .path(REQUEST_MAPPING + "/{id}")
                .buildAndExpand(orderResponseDto.id())
                .toUriString();
        return responseBuilder.buildResponse(HttpStatus.CREATED, message, savedOrderUri, Map.of("saved_order_response", orderResponseDto));
    }
}
