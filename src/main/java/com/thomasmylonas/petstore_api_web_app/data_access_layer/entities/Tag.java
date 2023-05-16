package com.thomasmylonas.petstore_api_web_app.data_access_layer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;

@Entity(name = "PET_TAG")
@JsonPropertyOrder({"id", "name"})
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @SequenceGenerator(name = "generator", sequenceName = "ID_SEQUENCE_TAG", allocationSize = 1)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "TAG_NAME", nullable = false, length = 20)
    private String name;

    // Mappings - ManyToOne
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PET_ID")
    @JsonIgnore
    private Pet pet;

    public Tag() {
    }

    public Tag(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /*@com.fasterxml.jackson.annotation.JsonCreator(mode = com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES)
    public Tag(@com.fasterxml.jackson.annotation.JsonProperty Long id, @com.fasterxml.jackson.annotation.JsonProperty String name) {
        this.id = id;
        this.name = name;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
