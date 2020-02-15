package com.thomasmylonas.petstore_api_web_app.data_access.entities;

import com.thomasmylonas.petstore_api_web_app.models.PetModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "PET")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @SequenceGenerator(name = "generator", sequenceName = "ID_SEQUENCE_PET", allocationSize = 1)
    @Column(name = "ID", updatable = false, nullable = false)
    private Integer id;
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
    @OneToMany(mappedBy = "pet", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Tag> tags;
    @OneToMany(mappedBy = "pet", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<PhotoUrl> photoUrls;

    public Pet() {
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
