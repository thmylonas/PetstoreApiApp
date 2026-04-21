package com.thomasmylonas.petstore_api_app.repositories;

import com.thomasmylonas.petstore_api_app.dtos.pet_dtos.InventoryResponseDto;
import com.thomasmylonas.petstore_api_app.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * SELECT p.STATUS, SUM(o.QUANTITY) QUANTITies FROM ORDERS o inner join PETS p ON o.PET_ID = p.ID group by  p.STATUS;
     *
     * @return An Optional of "List<InventoryResponseDto>"
     */
    @Query(value = """
            select p.status status, sum(o.quantity) quantities from Order o inner join o.pet p group by p.status
            """)
    Optional<List<InventoryResponseDto>> findInventoriesByPetStatus();
}

/*
//org.hibernate.query.SemanticException: Missing constructor for type 'InventoryResponseDto' [select new com.thomasmylonas.petstore_api_app.dtos.pet_dtos.InventoryResponseDto(p.status, sum(o.quantity)) from Order o inner join o.pet p group by p.status]
//[select new InventoryResponseDto(p.status, sum(o.quantity)) from Order o inner join o.pet p group by p.status]
// select p1_0.status,sum(o1_0.quantity) from orders o1_0 join pets p1_0 on p1_0.id=o1_0.pet_id group by p1_0.status
*/
