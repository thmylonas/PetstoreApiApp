package com.thomasmylonas.petstore_api_web_app.data_access_layer.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "Status")
@Table(name = "Status")
@Data
@NoArgsConstructor
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
    private Long id;

    @Column(name = "STATUS_NAME", nullable = false, length = 15)
    private String name;

    // Mappings - OneToMany
    @OneToMany(mappedBy = "status", cascade = CascadeType.REFRESH)
    private List<Pet> petList;

    public Status(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
