package com.thomasmylonas.petstore_api_web_app.data_access_layer.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "PhotoUrl")
@Table(name = "PhotoUrls")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhotoUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Photo_Url_Generator")
    @SequenceGenerator(name = "Photo_Url_Generator", sequenceName = "Photo_Url_Sequence", initialValue = 1, allocationSize = 1)
    @Column(name = "Photo_Url_Id")
    private Long id;

    @Column(name = "Photo_Url_Name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "Pet_Id")
    private Pet pet;
}
