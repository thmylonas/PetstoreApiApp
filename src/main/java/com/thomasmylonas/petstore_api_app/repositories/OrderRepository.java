package com.thomasmylonas.petstore_api_app.repositories;

import com.thomasmylonas.petstore_api_app.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
