package com.thomasmylonas.petstore_api_app.services;

import com.thomasmylonas.petstore_api_app.api.dtos.order_dtos.OrderRequestDto;
import com.thomasmylonas.petstore_api_app.api.dtos.order_dtos.OrderResponseDto;
import com.thomasmylonas.petstore_api_app.api.dtos.pet_dtos.InventoryResponseDto;
import com.thomasmylonas.petstore_api_app.api.entities.Order;
import com.thomasmylonas.petstore_api_app.api.entities.Pet;
import com.thomasmylonas.petstore_api_app.api.exceptions.RequestedResourceNotFoundException;
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
    public List<InventoryResponseDto> findInventoriesByPetStatus() {
        return orderRepository.findInventoriesByPetStatus()
                .orElseThrow(() -> new RequestedResourceNotFoundException("The Inventories by PetStatus are not found!"));
    }

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

        // TODO: Check if the Pet is in stock inside the inventory

        Order order = orderMapper.toOrder(orderRequestDto);
        order.setPet(pet);
        order.setShipDate(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);
        return orderMapper.fromOrder(savedOrder);
    }

    @Override
    public List<OrderResponseDto> saveAllOrders(List<OrderRequestDto> orderRequestDtos) {
        return orderRequestDtos.stream().map(this::saveOrder).toList();
    }

    @Override
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }
}
