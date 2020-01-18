package com.thomasmylonas.petstore_api_web_app.data_access.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import java.util.List;

@Entity(name = "STATUS")
@JsonPropertyOrder({"id", "name"})
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @SequenceGenerator(name = "generator", sequenceName = "ID_SEQUENCE_STATUS", allocationSize = 1)
    @Column(name = "ID", updatable = false, nullable = false)
    private Integer id;
    @Column(name = "STATUS_NAME", nullable = false, length = 15)
    private String name;

    // Mappings - OneToMany
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "status")
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
        return "Status{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
