package com.thomasmylonas.petstore_api_web_app.data_access.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import java.util.List;

@Entity(name = "STATUS")
@JsonPropertyOrder({"id", "name"})
public class Status {

    public enum StatusEnum {
        AVAILABLE,
        PENDING,
        SOLD;

        static public Integer getId(String name) {

            if (name.equalsIgnoreCase(AVAILABLE.name())) {
                return 1;
            } else if (name.equalsIgnoreCase(PENDING.name())) {
                return 2;
            } else if (name.equalsIgnoreCase(SOLD.name())) {
                return 3;
            }
            return -1;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @SequenceGenerator(name = "generator", sequenceName = "ID_SEQUENCE_STATUS", allocationSize = 1)
    @Column(name = "ID", updatable = false, nullable = false)
    @JsonIgnore
    private Integer id;
    @Column(name = "STATUS_NAME", nullable = false, length = 15)
    private String name;

    // Mappings - OneToMany
    @OneToMany(mappedBy = "status", cascade = CascadeType.REFRESH)
    @JsonIgnore
    private List<Pet> petList;

    public Status() {
    }

    public Status(Integer id,
                  String name) {
        this.id = id;
        this.name = name;
    }

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
