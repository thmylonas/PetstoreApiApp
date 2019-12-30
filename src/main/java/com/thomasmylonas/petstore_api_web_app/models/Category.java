package com.thomasmylonas.petstore_api_web_app.models;

import javax.persistence.*;
import java.util.List;

@Entity(name = "PET_CATEGORY")
//@Table(schema = "petstoredb", name = "PET_CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    private Integer id;
    @Column(name = "CATEGORY_NAME", nullable = false, length = 25)
    private String name;

    // Mappings - OneToMany
    @OneToMany(mappedBy = "category")
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

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
