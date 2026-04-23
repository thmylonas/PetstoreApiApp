package com.thomasmylonas.petstore_api_app.repositories;

import com.thomasmylonas.petstore_api_app.dtos.pet_dtos.InventoryResponseDto;
import com.thomasmylonas.petstore_api_app.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * SELECT p.STATUS PET_STATUS, SUM(o.QUANTITY) QUANTITIES FROM ORDERS o inner join PETS p ON o.PET_ID = p.ID group by  p.STATUS;
     *
     * @return An Optional of "List<InventoryResponseDto>"
     */
    @Query(value = """
            select p.status status, sum(o.quantity) quantities from Order o inner join o.pet p group by p.status
            """)
    Optional<List<InventoryResponseDto>> findInventoriesByPetStatus();
}
