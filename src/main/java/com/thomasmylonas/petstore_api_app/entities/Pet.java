package com.thomasmylonas.petstore_api_app.entities;

import com.thomasmylonas.petstore_api_app.enums.PetStatus;
import jakarta.persistence.CascadeType;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Pet")
@Table(name = "Pets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Pets_Sequence_Generator")
    @SequenceGenerator(name = "Pets_Sequence_Generator", sequenceName = "Pets_Sequence", initialValue = 1, allocationSize = 1)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Name")
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "Status")
    private PetStatus status;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Category_Id")
    private Category category;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.PERSIST)
    private List<Tag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "pet", cascade = CascadeType.PERSIST)
    private List<PhotoUrl> photoUrls = new ArrayList<>();

    public void addCategory(Category category) {
        category.getPets().add(this);
        setCategory(category);
    }

    public void addTag(Tag tag) {
        tag.setPet(this);
        tags.add(tag);
    }

    public void addPhotoUrl(PhotoUrl photoUrl) {
        photoUrl.setPet(this);
        photoUrls.add(photoUrl);
    }
}

/*
{
  "name": "tom",
  "status": "available",
  "category": {
    "name": "cat"
  },
  "tags": [
    {
      "name": "tom123"
    }
  ],
  "photo_urls": [
    {
      "name": "htttp://www.cats.tom.com"
    }
  ]
}
-----------------------------------------------------------------
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
