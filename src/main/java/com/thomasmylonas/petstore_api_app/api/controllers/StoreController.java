package com.thomasmylonas.petstore_api_app.api.controllers;

import com.thomasmylonas.petstore_api_app.api.dtos.order_dtos.OrderRequestDto;
import com.thomasmylonas.petstore_api_app.api.dtos.order_dtos.OrderResponseDto;
import com.thomasmylonas.petstore_api_app.api.dtos.pet_dtos.InventoryResponseDto;
import com.thomasmylonas.petstore_api_app.api.services.OrderService;
import com.thomasmylonas.petstore_api_app.api.models.ResponseBuilder;
import com.thomasmylonas.petstore_api_app.api.models.ResponseSuccess;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping(path = {"/api/v1/store"})
@RequiredArgsConstructor
@Validated
public class StoreController {

    private static final String REQUEST_MAPPING = "/api/v1/store";

    private final OrderService orderService;
    private final ResponseBuilder responseBuilder;

    /**
     * Endpoint:
     * - GET, "http://localhost:8080/api/v1/store/inventory"
     *
     * @return The ResponseEntity<ResponseSuccess>
     */
    @GetMapping(path = {"/inventory"})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> findInventoriesByPetStatus() {
        final String message = "Success: The Inventories by PetStatus are found!";
        List<InventoryResponseDto> inventoryResponseDtos = orderService.findInventoriesByPetStatus();
        return responseBuilder.buildResponseSuccess(HttpStatus.OK, message, Map.of("inventories_by_status", inventoryResponseDtos));
    }

    /**
     * Endpoint:
     * - GET, "http://localhost:8080/api/v1/store/order/{id}"
     *
     * @param orderId The "orderId"
     * @return The ResponseEntity<ResponseSuccess>
     */
    @GetMapping(path = {"/order/{id}"})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> findOrderById(@PathVariable(value = "id")
                                                         @PositiveOrZero(message = "The 'id' must be a positive number or 0")
                                                         Long orderId) {
        final String message = "Success: The Order with ID " + orderId + " is found!";
        OrderResponseDto orderResponseDto = orderService.findOrderById(orderId);
        return responseBuilder.buildResponseSuccess(HttpStatus.OK, message, Map.of("order_response", orderResponseDto));
    }

    /**
     * Endpoint:
     * - GET, "http://localhost:8080/api/v1/store/orders"
     *
     * @return The ResponseEntity<ResponseSuccess>
     */
    @GetMapping(path = {"/orders"})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> findAllOrders() {
        final String message = "Success: The Orders are found!";
        List<OrderResponseDto> orderResponseDtos = orderService.findAllOrders();
        return responseBuilder.buildResponseSuccess(HttpStatus.OK, message, Map.of("orders_response", orderResponseDtos));
    }

    /**
     * Endpoint:
     * - POST, "http://localhost:8080/api/v1/store/order"
     *
     * @param orderRequestDto The "orderRequestDto"
     * @return The ResponseEntity<ResponseSuccess>
     */
    @PostMapping(path = {"/order"})
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseSuccess> saveOrder(@RequestBody @Valid OrderRequestDto orderRequestDto) {

        final String message = "Created: The Order has been created successfully!";
        OrderResponseDto orderResponseDto = orderService.saveOrder(orderRequestDto);
        String savedOrderUri = ServletUriComponentsBuilder
                .fromCurrentContextPath() // "http://localhost:8080"
                .path(REQUEST_MAPPING + "/{id}")
                .buildAndExpand(orderResponseDto.id())
                .toUriString();
        return responseBuilder.buildResponseSuccess(HttpStatus.CREATED, message, savedOrderUri, Map.of("saved_order_response", orderResponseDto));
    }

    /**
     * Endpoint:
     * - POST, "http://localhost:8080/api/v1/store/order/all"
     *
     * @param orderRequestDtos The "orderRequestDtos"
     * @return The ResponseEntity<ResponseSuccess>
     */
    @PostMapping(path = {"/order/all"})
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseSuccess> saveAllOrders(@RequestBody
                                                         @NotEmpty(message = "The 'orderRequestDtos' must not be null or empty")
                                                         List<@Valid OrderRequestDto> orderRequestDtos) {
        final String message = "Created: The Orders have been created successfully!";
        List<OrderResponseDto> savedOrderResponseDtos = orderService.saveAllOrders(orderRequestDtos);
        return responseBuilder.buildResponseSuccess(HttpStatus.CREATED, message, Map.of("saved_orders_response", savedOrderResponseDtos));
    }

    /**
     * Endpoint:
     * - DELETE, "http://localhost:8080/api/v1/store/order/{id}"
     *
     * @param orderId The "orderId"
     * @return The ResponseEntity<ResponseSuccess>
     */
    @DeleteMapping(path = {"/order/{id}"})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> deleteOrderById(@PathVariable(value = "id")
                                                           @PositiveOrZero(message = "The 'id' must be a positive number or 0")
                                                           Long orderId) {
        final String message = "Success: The Order with ID " + orderId + " has been deleted successfully!";
        orderService.deleteOrderById(orderId);
        return responseBuilder.buildResponseSuccess(HttpStatus.OK, message, Map.of("message", message));
    }
}
