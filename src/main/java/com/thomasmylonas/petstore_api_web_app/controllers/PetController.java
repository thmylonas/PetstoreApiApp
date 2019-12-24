package com.thomasmylonas.petstore_api_web_app.controllers;

import com.thomasmylonas.petstore_api_web_app.daos.PetDao;
import com.thomasmylonas.petstore_api_web_app.models.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
@RequestMapping(path = "/")
public class PetController implements WebMvcConfigurer {

    @Autowired
    private PetDao petDao;

    @RequestMapping(path = "pet/{id}", method = RequestMethod.GET)
    public Pet getById(@PathVariable int id) {
        System.out.println("The pet with id = " + id + ", is the\n" + petDao.getOne(id));
        return petDao.getOne(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getHome() {
        return "home";
    }

    @RequestMapping(path = "pet", method = RequestMethod.GET)
    public String getPetPage() {
        System.out.println("The pet with id = 1, is the\n" + petDao.getOne(1));
        return "pet";
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
