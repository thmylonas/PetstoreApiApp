package com.thomasmylonas.petstore_api_web_app.views_backingbeans;

import com.thomasmylonas.petstore_api_web_app.daos.PetDao;
import com.thomasmylonas.petstore_api_web_app.models.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PetBackingBean {

//    @Autowired
//    private PetDao petDao;

    private Integer id;
    private String name;
    private String type;
    private int age;

    public PetBackingBean() {
//        this.id = petDao.getOne(1).getId();
//        this.name = petDao.getOne(1).getName();
//        this.type = petDao.getOne(1).getType();
//        this.age = petDao.getOne(1).getAge();
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
}
