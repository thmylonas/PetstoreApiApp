package com.thomasmylonas.petstore_api_web_app.data_access_layer.entities;

import com.thomasmylonas.petstore_api_web_app.service_layer.models.enums.StatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity(name = "Pet")
@Table(name = "Pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Pet_Generator")
    @SequenceGenerator(name = "Pet_Generator", sequenceName = "Pet_Sequence", initialValue = 1, allocationSize = 1)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Status")
    @Enumerated(value = EnumType.STRING)
    private StatusEnum status;

//    private Category category;
//    private List<String> photoUrls;
//    private List<Tag> tags;
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
