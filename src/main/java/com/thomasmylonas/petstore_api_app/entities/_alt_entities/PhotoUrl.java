package com.thomasmylonas.petstore_api_app.entities._alt_entities;

import com.thomasmylonas.petstore_api_app.entities.Pet;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "PhotoUrl")
@Table(name = "Photo_Url")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhotoUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @SequenceGenerator(name = "generator", sequenceName = "ID_SEQUENCE_PHOTO_URL", allocationSize = 1)
    @Column(name = "Id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "URL_NAME", nullable = false, length = 1999)
    private String name;

    // Mappings - ManyToOne
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PET_ID")
    private Pet pet;

    public PhotoUrl(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
