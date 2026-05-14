package com.thomasmylonas.petstore_api_app.services;

import com.thomasmylonas.petstore_api_app.dtos.order_dtos.OrderResponseDto;
import com.thomasmylonas.petstore_api_app.dtos.pet_dtos.InventoryResponseDto;
import com.thomasmylonas.petstore_api_app.entities.Order;
import com.thomasmylonas.petstore_api_app.enums.OrderStatus;
import com.thomasmylonas.petstore_api_app.enums.PetStatus;
import com.thomasmylonas.petstore_api_app.repositories.OrderRepository;
import com.thomasmylonas.petstore_api_app.services.mappers.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class OrderServiceImplTest {

    @Mock
    private OrderRepository mockOrderRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFindInventoriesByPetStatus() {

        // Given
        final List<InventoryResponseDto> INVENTORY_RESPONSE_DTOS = List.of(InventoryResponseDto.builder()
                        .status(PetStatus.AVAILABLE)
                        .quantities(3L)
                        .build(),
                InventoryResponseDto.builder()
                        .status(PetStatus.SOLD)
                        .quantities(5L)
                        .build(),
                InventoryResponseDto.builder()
                        .status(PetStatus.PENDING)
                        .quantities(2L)
                        .build()
        );

        // When
        when(mockOrderRepository.findInventoriesByPetStatus()).thenReturn(Optional.of(INVENTORY_RESPONSE_DTOS));

        // Then
        List<InventoryResponseDto> inventoriesByPetStatus = orderService.findInventoriesByPetStatus();
        log.info("InventoriesByPetStatus: {}", inventoriesByPetStatus);
        assertEquals(INVENTORY_RESPONSE_DTOS, inventoriesByPetStatus);
    }

    @Test
    void testFindOrderById() {

        // Given
        final Long ORDER_ID = 1L;
        final Order ORDER_BY_ID = Order.builder()
                .id(ORDER_ID)
                .quantity(4)
                .shipDate(LocalDateTime.now())
                .status(OrderStatus.PLACED)
                .complete(true)
                .build();
        final OrderResponseDto ORDER_RESPONSE_DTO = OrderResponseDto.builder()
                .id(ORDER_ID)
                .petId(2L)
                .quantity(4)
                .shipDate(LocalDateTime.now())
                .status(OrderStatus.PLACED.getValue())
                .complete(true)
                .build();

        // When
        when(mockOrderRepository.findById(ORDER_ID)).thenReturn(Optional.of(ORDER_BY_ID));
        when(orderMapper.fromOrder(ORDER_BY_ID)).thenReturn(ORDER_RESPONSE_DTO);

        // Then
        OrderResponseDto orderById = orderService.findOrderById(1L);
        log.info("OrderById: {}", orderById);
        assertEquals(ORDER_RESPONSE_DTO, orderById);
    }

    @Test
    void testFindAllOrders() {
    }

    @Test
    void testSaveOrder() {
    }

    @Test
    void testSaveAllOrders() {
    }

    @Test
    void testDeleteOrderById() {
    }
}
