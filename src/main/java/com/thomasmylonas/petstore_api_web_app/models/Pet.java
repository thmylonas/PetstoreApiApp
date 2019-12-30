package com.thomasmylonas.petstore_api_web_app.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import java.util.List;

@Entity(name = "PET")
@JsonPropertyOrder({"id", "category", "name", "photoUrls", "tags", "status"})
//@Table(schema = "petstoredb", name = "PET")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    private Integer id;
    @Column(name = "PET_NAME", nullable = false, length = 25)
    private String name;
    @Lob
    @Column(name = "PHOTO_URLS", nullable = false)
    private String photoUrls;

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

    public String getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(String photoUrls) {
        this.photoUrls = photoUrls;
    }

    public String getStatus() {
        return status.getName();
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

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", photoUrls='" + photoUrls + '\'' +
                ", status=" + status +
                ", category=" + category +
                ", tagList=" + tags +
                '}';
    }
}
