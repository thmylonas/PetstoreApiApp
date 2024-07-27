package com.thomasmylonas.petstore_api_web_app.data_access_layer.entities;

import com.thomasmylonas.petstore_api_web_app.service_layer.models.PetModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Pet")
@Table(name = "Pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @SequenceGenerator(name = "generator", sequenceName = "ID_SEQUENCE_PET", allocationSize = 1)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;
    @Column(name = "PET_NAME", nullable = false, length = 25)
    private String name;

    // Mappings - ManyToOne
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STATUS_ID")
    private Status status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PET_CATEGORY_ID")
    private Category category;

    // Mappings - OneToMany
    @OneToMany(mappedBy = "pet", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Tag> tags;
    @OneToMany(mappedBy = "pet", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<PhotoUrl> photoUrls;

    public Pet() {
    }

    public Pet(Long id, String name, Status status, Category category, List<Tag> tags, List<PhotoUrl> photoUrls) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.category = category;
        this.tags = tags;
        this.photoUrls = photoUrls;
    }

    public Pet(PetModel petModel) {
        this.id = petModel.getId();
        this.name = petModel.getName();
        this.status = new Status(Status.StatusEnum.getId(petModel.getStatus()), petModel.getStatus());
        this.category = petModel.getCategory();
        this.tags = petModel.getTags();
        photoUrls = new ArrayList<>();
        for (int i = 0; i < petModel.getPhotoUrls().size(); i++) {
            photoUrls.add(new PhotoUrl(null, petModel.getPhotoUrls().get(i)));
        }
    }

    /*@com.fasterxml.jackson.annotation.JsonCreator(mode = com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES)
    public Pet(@com.fasterxml.jackson.annotation.JsonProperty("id") Long id,
               @com.fasterxml.jackson.annotation.JsonProperty("name") String name,
               @com.fasterxml.jackson.annotation.JsonProperty Status status,
               @com.fasterxml.jackson.annotation.JsonProperty("category") Category category,
               @com.fasterxml.jackson.annotation.JsonProperty("tags") List<Tag> tags,
               @com.fasterxml.jackson.annotation.JsonProperty("photoUrls") List<String> photoUrls) {
        this.id = id;
        this.name = name;
        this.status.setName(status.getName());
        this.category = category;
        this.tags = tags;
        for (int i = 0; i < photoUrls.size(); i++) {
            this.photoUrls.get(i).setName(photoUrls.get(i));
        }
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        tags.stream().forEach(tag -> {
            tag.setPet(this);
        });
        this.tags = tags;
    }

    public List<PhotoUrl> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<PhotoUrl> photoUrls) {

        photoUrls.stream().forEach(photoUrl -> {
            photoUrl.setPet(this);
        });
        this.photoUrls = photoUrls;
        /*for (int i = 0; i < photoUrls.size(); i++) {
            this.photoUrls.get(i).setName(photoUrls.get(i).getName());
        }*/
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + getStatus() +
                ", category=" + category +
                ", tags=" + tags +
                ", photoUrls=" + getPhotoUrls() +
                '}';
    }
}
