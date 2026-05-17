package com.thomasmylonas.petstore_api_app.services;

import com.thomasmylonas.petstore_api_app.dtos.order_dtos.OrderRequestDto;
import com.thomasmylonas.petstore_api_app.enums.OrderStatus;
import com.thomasmylonas.petstore_api_app.exceptions.RequestedResourceNotFoundException;
import com.thomasmylonas.petstore_api_app.repositories.OrderRepository;
import com.thomasmylonas.petstore_api_app.repositories.PetRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class OrderServiceImplFailureTest {

    @Mock
    private OrderRepository mockOrderRepository;

    @Mock
    private PetRepository mockPetRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName(value = "When: FindInventoriesByPetStatus is called, Then: RequestedResourceNotFoundException is thrown")
    public void test_When_FindInventoriesByPetStatusIsCalled_Then_RequestedResourceNotFoundExceptionIsThrown() {

        // Given / Arrange

        when(mockOrderRepository.findInventoriesByPetStatus()).thenThrow(RequestedResourceNotFoundException.class);

        // When / Act - Then / Assert

        assertThrows(RequestedResourceNotFoundException.class, () -> orderService.findInventoriesByPetStatus());
    }

    @Test
    @DisplayName(value = "When: FindOrderById is called, Then: RequestedResourceNotFoundException is thrown")
    public void test_When_FindOrderByIdIsCalled_Then_RequestedResourceNotFoundExceptionIsThrown() {

        // Given / Arrange

        final Long ORDER_ID = 1L;
        when(mockOrderRepository.findById(ORDER_ID)).thenThrow(RequestedResourceNotFoundException.class);

        // When / Act - Then / Assert

        assertThrows(RequestedResourceNotFoundException.class, () -> orderService.findOrderById(ORDER_ID));
    }

    @Test
    @DisplayName(value = "Given: Order, When: saveOrder is called, Then: RequestedResourceNotFoundException is thrown")
    public void test_Given_Order_When_SaveOrderIsCalled_Then_RequestedResourceNotFoundExceptionIsThrown() {

        // Given / Arrange

        final Long PET_ID = 2L;
        final OrderRequestDto ORDER_REQUEST_DTO = OrderRequestDto.builder()
                .petId(PET_ID)
                .quantity(4)
                .status(OrderStatus.PLACED.getValue())
                .complete(true)
                .build();
        //final Order ORDER = Order.builder().quantity(4).status(OrderStatus.PLACED).complete(true).build();

        when(mockPetRepository.findById(PET_ID)).thenThrow(RequestedResourceNotFoundException.class);
        //when(mockOrderRepository.save(ORDER)).thenThrow(IllegalArgumentException.class); // Will never happen, because of the "RequestedResourceNotFoundException"

        // When / Act -  Then / Assert

        assertThrows(RequestedResourceNotFoundException.class, () -> orderService.saveOrder(ORDER_REQUEST_DTO));
        //assertThrows(IllegalArgumentException.class, () -> orderService.saveOrder(ORDER_REQUEST_DTO)); // Will never happen, because of the "RequestedResourceNotFoundException"
    }

    @Test
    @DisplayName(value = "Given: OrderId, When: DeleteOrderById is called, Then: IllegalArgumentException is thrown")
    public void test_Given_OrderId_When_DeleteOrderByIdIsCalled_Then_IllegalArgumentExceptionIsThrown() {

        // Given / Arrange

        final Long ORDER_ID = 1L;
        doThrow(IllegalArgumentException.class).when(mockOrderRepository).deleteById(ORDER_ID);

        // When / Act - Then / Assert

        assertThrows(IllegalArgumentException.class, () -> orderService.deleteOrderById(ORDER_ID));
    }
}
