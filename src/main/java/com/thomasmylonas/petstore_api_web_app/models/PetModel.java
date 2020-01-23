package com.thomasmylonas.petstore_api_web_app.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.thomasmylonas.petstore_api_web_app.data_access.entities.*;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({"id", "category", "name", "photoUrls", "tags", "status"})
public class PetModel {

    private Integer id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tag> tags;
    private String status;

    public PetModel() {
    }

    public PetModel(Pet pet) {
        this.id = pet.getId();
        this.name = pet.getName();
        this.status = pet.getStatus().getName();
        this.category = pet.getCategory();
        this.tags = pet.getTags();
        photoUrls = new ArrayList<>();
        for (int i = 0; i < pet.getPhotoUrls().size(); i++) {
            photoUrls.add(pet.getPhotoUrls().get(i).getName());
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", category=" + category +
                ", tags=" + tags +
                ", photoUrls=" + photoUrls +
                '}';
    }
}
