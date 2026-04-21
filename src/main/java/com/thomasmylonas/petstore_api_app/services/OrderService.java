package com.thomasmylonas.petstore_api_app.services;

import com.thomasmylonas.petstore_api_app.dtos.order_dtos.OrderRequestDto;
import com.thomasmylonas.petstore_api_app.dtos.order_dtos.OrderResponseDto;
import com.thomasmylonas.petstore_api_app.dtos.pet_dtos.InventoryResponseDto;
import com.thomasmylonas.petstore_api_app.exceptions.RequestedResourceNotFoundException;

import java.util.List;

public interface OrderService {

    List<InventoryResponseDto> findInventoriesByPetStatus();

    OrderResponseDto findOrderById(Long id) throws RequestedResourceNotFoundException;

    List<OrderResponseDto> findAllOrders();

    OrderResponseDto saveOrder(OrderRequestDto orderRequestDto);

    List<OrderResponseDto> saveAllOrders(List<OrderRequestDto> orderRequestDtos);

    void deleteOrderById(Long id) throws RequestedResourceNotFoundException;
}
