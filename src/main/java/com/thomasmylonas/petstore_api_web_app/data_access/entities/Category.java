package com.thomasmylonas.petstore_api_web_app.data_access.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import java.util.List;

@Entity(name = "PET_CATEGORY")
@JsonPropertyOrder({"id", "name"})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @SequenceGenerator(name = "generator", sequenceName = "ID_SEQUENCE_CATEGORY", allocationSize = 1)
    @Column(name = "ID", updatable = false, nullable = false)
    private Integer id;
    @Column(name = "CATEGORY_NAME", nullable = false, length = 25)
    private String name;

    // Mappings - OneToMany
    @OneToMany(mappedBy = "category", cascade = CascadeType.REFRESH)
    @JsonIgnore
    private List<Pet> petList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pet> getPetList() {
        return petList;
    }

    public void setPetList(List<Pet> petList) {
        this.petList = petList;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
