package com.thomasmylonas.petstore_api_app.api.services.mappers;

import com.thomasmylonas.petstore_api_app.api.dtos.order_dtos.OrderRequestDto;
import com.thomasmylonas.petstore_api_app.api.dtos.order_dtos.OrderResponseDto;
import com.thomasmylonas.petstore_api_app.api.entities.Order;
import com.thomasmylonas.petstore_api_app.api.enums.OrderStatus;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public Order toOrder(OrderRequestDto orderRequestDto) {
        return Order.builder()
                .quantity(orderRequestDto.quantity())
                .status(OrderStatus.valueOfOrderStatus(orderRequestDto.status()))
                .complete(orderRequestDto.complete())
                .build();
    }

    public OrderResponseDto fromOrder(Order order) {
        return OrderResponseDto.builder()
                .id(order.getId())
                .petId(order.getPet().getId())
                .quantity(order.getQuantity())
                .shipDate(order.getShipDate())
                .status(order.getStatus().getValue())
                .complete(order.isComplete())
                .build();
    }
}
