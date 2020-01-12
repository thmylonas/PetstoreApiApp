package com.thomasmylonas.petstore_api_web_app.data_access.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;

@Entity(name = "PHOTO_URLS")
@JsonPropertyOrder({"id", "name"})
public class PhotoUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    private Integer id;
    @Column(name = "URL_NAME", nullable = false, length = 1999)
    private String name;

    // Mappings - ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
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
        return "PhotoUrl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
