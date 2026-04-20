package com.thomasmylonas.petstore_api_app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

@Entity(name = "Category")
@Table(name = "Categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Categories_Sequence_Generator")
    @SequenceGenerator(name = "Categories_Sequence_Generator", sequenceName = "Categories_Sequence", initialValue = 1, allocationSize = 1)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Name")
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Pet> pets = new ArrayList<>();

    // This method is not needed (I do not know why)
    public void addPet(Pet pet) {
        getPets().add(pet);
        pet.setCategory(this);
    }
}

/*
Swagger model:
------------------
Category{
    id	    integer($int64)
    name	string
}
*/
