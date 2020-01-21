package com.thomasmylonas.petstore_api_web_app.data_access.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "PET")
@JsonPropertyOrder({"id", "category", "name", "photoUrls", "tags", "status"})
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

    public String getStatus() {
        return status.getName();
    }

    public Status getStatus(int unused) {
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

    public List<String> getPhotoUrls() {
        return photoUrls.stream().map(PhotoUrl::getName).collect(Collectors.toList());
    }

    public void setPhotoUrls(List<PhotoUrl> photoUrls) {
        photoUrls.stream().forEach(photoUrl -> {
            photoUrl.setPet(this);
        });
        this.photoUrls = photoUrls;
    }
//    public void setPhotoUrls(List<PhotoUrl> photoUrls) {
//        for (int i = 0; i < photoUrls.size(); i++) {
//            this.photoUrls.set(i, photoUrls.get(i));
//        }
//    }

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
