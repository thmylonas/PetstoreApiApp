package com.thomasmylonas.petstore_api_web_app.data_access_layer.entities;

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
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity(name = "Pet")
@Table(name = "Pet")
@Data
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
    private StatusEnum status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PET_CATEGORY_ID")
    @ToString.Exclude
    private Category category;

    // Mappings - OneToMany
    @OneToMany(mappedBy = "pet", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Tag> tags;

    @OneToMany(mappedBy = "pet", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<PhotoUrl> photoUrls;

    /*public Pet(PetModel petModel) {
        this.id = petModel.getId();
        this.name = petModel.getName();
        this.category = petModel.getCategory();
        this.tags = petModel.getTags();
        photoUrls = new ArrayList<>();
        for (int i = 0; i < petModel.getPhotoUrls().size(); i++) {
            photoUrls.add(new PhotoUrl(null, petModel.getPhotoUrls().get(i)));
        }
    }

    public void setTags(List<Tag> tags) {
        tags.stream().forEach(tag -> {
            tag.setPet(this);
        });
        this.tags = tags;
    }

    public void setPhotoUrls(List<PhotoUrl> photoUrls) {

        photoUrls.stream().forEach(photoUrl -> {
            photoUrl.setPet(this);
        });
        this.photoUrls = photoUrls;
//        for (int i = 0; i < photoUrls.size(); i++) {
//            this.photoUrls.get(i).setName(photoUrls.get(i).getName());
//        }
    }*/
}
