package com.thomasmylonas.petstore_api_app.controllers;

import com.thomasmylonas.petstore_api_app.dtos.order_dtos.OrderRequestDto;
import com.thomasmylonas.petstore_api_app.dtos.order_dtos.OrderResponseDto;
import com.thomasmylonas.petstore_api_app.dtos.pet_dtos.InventoryResponseDto;
import com.thomasmylonas.petstore_api_app.services.OrderService;
import com.thomasmylonas.petstore_api_app.models.ResponseBuilder;
import com.thomasmylonas.petstore_api_app.models.ResponseSuccess;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/store")
@RequiredArgsConstructor
@Slf4j
public class StoreController {

    private static final String REQUEST_MAPPING = "/api/v1/store";

    private final OrderService orderService;
    private final ResponseBuilder responseBuilder;

    @GetMapping(path = {"/inventory"})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> findInventoriesByPetStatus() { // "http://localhost:8080/api/v1/store/inventory"
        final String message = "Success: The Inventories by PetStatus are found!";
        List<InventoryResponseDto> inventoryResponseDtos = orderService.findInventoriesByPetStatus();
        return responseBuilder.buildResponse(HttpStatus.OK, message, Map.of("inventories_by_status", inventoryResponseDtos));
    }

    @GetMapping(path = {"/order/{id}"})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> findOrderById(@PathVariable(value = "id") Long id) { // "http://localhost:8080/api/v1/store/order/{id}"
        final String message = "Success: The Order with ID " + id + " is found!";
        OrderResponseDto orderResponseDto = orderService.findOrderById(id);
        return responseBuilder.buildResponse(HttpStatus.OK, message, Map.of("order_response", orderResponseDto));
    }

    @GetMapping(path = {"/orders"})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> findAllOrders() { // "http://localhost:8080/api/v1/store/orders"
        final String message = "Success: The Orders are found!";
        List<OrderResponseDto> orderResponseDtos = orderService.findAllOrders();
        return responseBuilder.buildResponse(HttpStatus.OK, message, Map.of("orders_response", orderResponseDtos));
    }

    @PostMapping(path = {"/order"})
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseSuccess> saveOrder(@RequestBody OrderRequestDto orderRequestDto) { // "http://localhost:8080/api/v1/store/order"

        final String message = "Created: The Order has been created successfully!";
        OrderResponseDto orderResponseDto = orderService.saveOrder(orderRequestDto);
        String savedOrderUri = ServletUriComponentsBuilder
                .fromCurrentContextPath() // "http://localhost:8080"
                .path(REQUEST_MAPPING + "/{id}")
                .buildAndExpand(orderResponseDto.id())
                .toUriString();
        return responseBuilder.buildResponse(HttpStatus.CREATED, message, savedOrderUri, Map.of("saved_order_response", orderResponseDto));
    }

    @PostMapping(path = {"/order/all"})
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseSuccess> saveAllOrders(@RequestBody List<OrderRequestDto> orderRequestDtos) { // "http://localhost:8080/api/v1/store/order/all"
        final String message = "Created: The Orders have been created successfully!";
        List<OrderResponseDto> savedOrderResponseDtos = orderService.saveAllOrders(orderRequestDtos);
        return responseBuilder.buildResponse(HttpStatus.CREATED, message, Map.of("saved_orders_response", savedOrderResponseDtos));
    }

    @DeleteMapping(path = {"/order/{id}"})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> deleteOrderById(@PathVariable(value = "id") Long id) { // "http://localhost:8080/api/v1/store/order/{id}"
        final String message = "Success: The Order with ID " + id + " has been deleted successfully!";
        orderService.deleteOrderById(id);
        return responseBuilder.buildResponse(HttpStatus.OK, message, Map.of("message", message));
    }
}
