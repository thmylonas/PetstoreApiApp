package com.thomasmylonas.petstore_api_web_app.entities._alt_entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "Pet")
@Table(name = "Pet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @SequenceGenerator(name = "generator", sequenceName = "ID_SEQUENCE_PET", allocationSize = 1)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "PET_NAME", nullable = false, length = 25)
    private String name;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PET_CATEGORY_ID")
    private Category category;

    // Mappings - OneToMany
    @OneToMany(mappedBy = "pet", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Tag> tags;

    @OneToMany(mappedBy = "pet", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<PhotoUrl> photoUrls;

    /*public void setTags(List<Tag> tags) {
        tags.forEach(tag -> {
            tag.setPet(this);
        });
        this.tags = tags;
    }

    public void setPhotoUrls(List<PhotoUrl> photoUrls) {

        photoUrls.forEach(photoUrl -> {
            photoUrl.setPet(this);
        });
        this.photoUrls = photoUrls;
        for (int i = 0; i < photoUrls.size(); i++) {
            this.photoUrls.get(i).setName(photoUrls.get(i).getName());
        }
    }*/
}
