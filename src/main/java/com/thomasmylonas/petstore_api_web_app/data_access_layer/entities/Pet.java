package com.thomasmylonas.petstore_api_web_app.data_access_layer.entities;

import com.thomasmylonas.petstore_api_web_app.service_layer.models.enums.PetStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "Pet")
@Table(name = "Pets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Pet_Generator")
    @SequenceGenerator(name = "Pet_Generator", sequenceName = "Pet_Sequence", initialValue = 1, allocationSize = 1)
    @Column(name = "Pet_Id")
    private Long id;

    @Column(name = "Pet_Name")
    private String name;

    @Column(name = "Pet_Status")
    @Enumerated(value = EnumType.STRING)
    private PetStatusEnum status;

    @ManyToOne
    @JoinColumn(name = "Category_Id")
    private Category category;

    @OneToMany(mappedBy = "pet")
    private List<Tag> tags;

//    private List<String> photoUrls;
}


/*
Pet{
    id	        integer($int64)
    category	Category{
                    id	integer($int64)
                    name	string // Dogs, Cats, Fish, Birds
                }
    name*	    string // example: doggie
    photoUrls*	[
        xml: OrderedMap { "wrapped": true }
        string
        xml: OrderedMap { "name": "photoUrl" }
        xml: name: photoUrl
       ]
       	// [xml: OrderedMap { "name": "photoUrl", "wrapped": true }string]
    tags	[
        xml: OrderedMap { "wrapped": true }
        Tag{
            id	integer($int64)
            name	string
        }
    ]
    status	    string Enum: [ available, pending, sold ] // pet status in the store
}

* Required - Needs custom validation

{
  "id": 0,
  "category": {
    "id": 0,
    "name": "string"
  },
  "name": "doggie",
  "photoUrls": [
    "string"
  ],
  "tags": [
    {
      "id": 0,
      "name": "string"
    }
  ],
  "status": "available"
}
*/
