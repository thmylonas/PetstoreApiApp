package com.thomasmylonas.petstore_api_web_app.data_access.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import java.util.List;

@Entity(name = "PET")
@JsonPropertyOrder({"id", "category", "name", "photoUrls", "tags", "status"})
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @OneToMany(mappedBy = "pet")
    private List<Tag> tags;
    @OneToMany(mappedBy = "pet")
    private List<PhotoUrl> photoUrls;

    public Pet() {
    }

    public Pet(Integer id,
               String name,
               Status status,
               Category category,
               List<Tag> tags, List<PhotoUrl> photoUrls) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.category = category;
        this.tags = tags;
        this.photoUrls = photoUrls;
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
        this.tags = tags;
    }

    public List<PhotoUrl> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<PhotoUrl> photoUrls) {
        this.photoUrls = photoUrls;
    }
}
