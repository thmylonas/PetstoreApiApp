package com.thomasmylonas.petstore_api_app.api.entities;

import com.thomasmylonas.petstore_api_app.api.enums.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "Order")
@Table(name = "Orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Orders_Id_Seq_Generator")
    @SequenceGenerator(name = "Orders_Id_Seq_Generator", sequenceName = "Orders_Id_Seq", initialValue = 1, allocationSize = 1)
    @Column(name = "Id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Pet_Id", referencedColumnName = "Id")
    private Pet pet;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "Ship_Date")
    private LocalDateTime shipDate;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "Status")
    private OrderStatus status;

    @Column(name = "Complete")
    private boolean complete;

    // This method is not needed
    public void addPet(Pet pet) {
        pet.getOrders().add(this);
        setPet(pet);
    }
}

/*
{
    "pet_id": 3,
    "quantity": 3,
    "status": "placed",
    "complete":	true
}
-----------------------------------------------------------------
Swagger model:
------------------
Order{
    id	        integer($int64)
    petId	    integer($int64)
    quantity	integer($int32)
    shipDate	string($date-time)
    status	    string Enum: [ placed, approved, delivered ] // Order Status
    complete	boolean
}
*/
