package com.thomasmylonas.petstore_api_web_app.models;

import javax.persistence.*;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    private Integer id;
    @Column(name = "PET_NAME", nullable = false)
    private String name;
    @Column(name = "PET_TYPE", nullable = false)
    private String type;
    @Column(name = "PET_AGE", nullable = false)
    private int age;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", age=" + age +
                '}';
    }
}
