package com.thomasmylonas.petstore_api_web_app.data_access.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;

@Entity(name = "PET_TAG")
@JsonPropertyOrder({"id", "name"})
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @SequenceGenerator(name = "generator", sequenceName = "ID_SEQUENCE_TAG", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Integer id;
    @Column(name = "TAG_NAME", nullable = false, length = 20)
    private String name;

    // Mappings - ManyToOne
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PET_ID")
    @JsonIgnore
    private Pet pet;

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

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
