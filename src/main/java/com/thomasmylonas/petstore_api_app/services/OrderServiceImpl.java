package com.thomasmylonas.petstore_api_app.services;

import com.thomasmylonas.petstore_api_app.dtos.order_dtos.OrderRequestDto;
import com.thomasmylonas.petstore_api_app.dtos.order_dtos.OrderResponseDto;
import com.thomasmylonas.petstore_api_app.entities.Order;
import com.thomasmylonas.petstore_api_app.entities.Pet;
import com.thomasmylonas.petstore_api_app.exceptions.RequestedResourceNotFoundException;
import com.thomasmylonas.petstore_api_app.repositories.OrderRepository;
import com.thomasmylonas.petstore_api_app.repositories.PetRepository;
import com.thomasmylonas.petstore_api_app.services.mappers.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service(value = "orderService")
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final PetRepository petRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponseDto findOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RequestedResourceNotFoundException(Order.class.getSimpleName(), id));
        return orderMapper.fromOrder(order);
    }

    @Override
    public List<OrderResponseDto> findAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::fromOrder).toList();
    }

    @Override
    public OrderResponseDto saveOrder(OrderRequestDto orderRequestDto) {

        Pet pet = petRepository.findById(orderRequestDto.petId())
                .orElseThrow(() -> new RequestedResourceNotFoundException(Pet.class.getSimpleName(), orderRequestDto.petId()));

        Order order = orderMapper.toOrder(orderRequestDto);
        order.setPet(pet);
        order.setShipDate(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);
        return orderMapper.fromOrder(savedOrder);
    }

    @Override
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }
}
