package com.thomasmylonas.petstore_api_web_app.entities;

import com.thomasmylonas.petstore_api_web_app.models.enums.OrderStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Order_Generator")
    @SequenceGenerator(name = "Order_Generator", sequenceName = "Order_Sequence", initialValue = 1, allocationSize = 1)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Pet_Id")
    private long petId;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "Ship_Date")
    private LocalDateTime shipDate;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "Status")
    private OrderStatusEnum status;

    @Column(name = "Complete")
    private boolean complete;
}

/*
Order{
    id	        integer($int64)
    petId	    integer($int64)
    quantity	integer($int32)
    shipDate	string($date-time)
    status	    string Enum: [ placed, approved, delivered ] // Order Status
    complete	boolean
}
*/
