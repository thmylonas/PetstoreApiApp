package com.thomasmylonas.petstore_api_app.services;

import com.thomasmylonas.petstore_api_app.dtos.order_dtos.OrderResponseDto;
import com.thomasmylonas.petstore_api_app.dtos.pet_dtos.InventoryResponseDto;
import com.thomasmylonas.petstore_api_app.entities.Order;
import com.thomasmylonas.petstore_api_app.enums.OrderStatus;
import com.thomasmylonas.petstore_api_app.enums.PetStatus;
import com.thomasmylonas.petstore_api_app.helpers.HelperClass;
import com.thomasmylonas.petstore_api_app.helpers.TestDataProvider;
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
import java.util.stream.Stream;

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
    public void testFindInventoriesByPetStatus() {

        // Given / Arrange
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

        // When / Act
        when(mockOrderRepository.findInventoriesByPetStatus()).thenReturn(Optional.of(INVENTORY_RESPONSE_DTOS));

        // Then / Assert
        List<InventoryResponseDto> inventoriesByPetStatus = orderService.findInventoriesByPetStatus();
        log.info("InventoriesByPetStatus: {}", inventoriesByPetStatus);
        assertEquals(INVENTORY_RESPONSE_DTOS, inventoriesByPetStatus);
    }

    @Test
    public void testFindOrderById() {

        // Given / Arrange
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

        // When / Act
        when(mockOrderRepository.findById(ORDER_ID)).thenReturn(Optional.of(ORDER_BY_ID));
        when(orderMapper.fromOrder(ORDER_BY_ID)).thenReturn(ORDER_RESPONSE_DTO);

        // Then / Assert
        OrderResponseDto orderById = orderService.findOrderById(1L);
        log.info("OrderById: {}", orderById);
        assertEquals(ORDER_RESPONSE_DTO, orderById);
    }

    @Test
    public void testFindAllOrders() {

        // Given / Arrange
        final List<Long> RANDOM_ORDER_IDS = Stream.generate(() -> HelperClass.RANDOM.nextLong(100)).limit(10).distinct().toList();
        final List<OrderResponseDto> ORDER_RESPONSE_DTOS = TestDataProvider.ORDER_REQUEST_DTOS.stream()
                .map(orderRequestDto -> OrderResponseDto.builder()
                        .id(RANDOM_ORDER_IDS.get(HelperClass.RANDOM.nextInt(RANDOM_ORDER_IDS.size())))
                        .petId(orderRequestDto.petId())
                        .quantity(orderRequestDto.quantity())
                        .shipDate(LocalDateTime.now())
                        .status(orderRequestDto.status())
                        .complete(orderRequestDto.complete())
                        .build()
                ).toList();
        final List<Order> ORDERS = TestDataProvider.ORDER_REQUEST_DTOS.stream()
                .map(orderRequestDto -> Order.builder()
                        .id(RANDOM_ORDER_IDS.get(HelperClass.RANDOM.nextInt(RANDOM_ORDER_IDS.size())))
                        .quantity(orderRequestDto.quantity())
                        .shipDate(LocalDateTime.now())
                        .status(OrderStatus.valueOfOrderStatus(orderRequestDto.status()))
                        .complete(orderRequestDto.complete())
                        .build()
                ).toList();

        // When / Act
        when(mockOrderRepository.findAll()).thenReturn(ORDERS);
        for (int i = 0; i < ORDERS.size(); i++) {
            when(orderMapper.fromOrder(ORDERS.get(i))).thenReturn(ORDER_RESPONSE_DTOS.get(i));
        }

        // Then / Assert
        List<OrderResponseDto> allOrders = orderService.findAllOrders();
        log.info("AllOrders: {}", allOrders);
        assertEquals(ORDER_RESPONSE_DTOS, allOrders);
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
