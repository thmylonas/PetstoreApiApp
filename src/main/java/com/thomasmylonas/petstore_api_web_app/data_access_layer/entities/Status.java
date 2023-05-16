package com.thomasmylonas.petstore_api_web_app.data_access_layer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import java.util.List;

/**
 * All the entities are implemented wrongly
 */
@Entity(name = "STATUS")
@JsonPropertyOrder({"id", "name"})
public class Status {

    public enum StatusEnum {
        AVAILABLE,
        PENDING,
        SOLD;

        static public Long getId(String name) {

            if (name.equalsIgnoreCase(AVAILABLE.name())) {
                return 1L;
            } else if (name.equalsIgnoreCase(PENDING.name())) {
                return 2L;
            } else if (name.equalsIgnoreCase(SOLD.name())) {
                return 3L;
            }
            return -1L;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @SequenceGenerator(name = "generator", sequenceName = "ID_SEQUENCE_STATUS", allocationSize = 1)
    @Column(name = "ID", updatable = false, nullable = false)
    @JsonIgnore
    private Long id;
    @Column(name = "STATUS_NAME", nullable = false, length = 15)
    private String name;

    // Mappings - OneToMany
    @OneToMany(mappedBy = "status", cascade = CascadeType.REFRESH)
    @JsonIgnore
    private List<Pet> petList;

    public Status() {
    }

    public Status(Long id, String name) {
        this.id = id;
        this.name = name;
    }

	/*@com.fasterxml.jackson.annotation.JsonCreator(mode = com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES)
    public Status(@com.fasterxml.jackson.annotation.JsonProperty Long id, @com.fasterxml.jackson.annotation.JsonProperty String name) {
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
