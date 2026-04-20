package com.thomasmylonas.petstore_api_app.services;

import com.thomasmylonas.petstore_api_app.dtos.order_dtos.OrderRequestDto;
import com.thomasmylonas.petstore_api_app.dtos.order_dtos.OrderResponseDto;

public interface OrderService {
    OrderResponseDto saveOrder(OrderRequestDto orderRequestDto);
}
