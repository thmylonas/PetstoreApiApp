package com.thomasmylonas.petstore_api_web_app.data_access_layer.entities;

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
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "Category")
@Table(name = "Categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Category_Generator")
    @SequenceGenerator(name = "Category_Generator", sequenceName = "Category_Sequence", initialValue = 1, allocationSize = 1)
    @Column(name = "Category_Id")
    private Long id;

    @Column(name = "Category_Name")
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Pet> pets;
}

/*
Category{
    id	    integer($int64)
    name	string
}
*/
