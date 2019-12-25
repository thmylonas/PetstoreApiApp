package com.thomasmylonas.petstore_api_web_app.controllers;

import com.thomasmylonas.petstore_api_web_app.config.DataAccessConfig;
import com.thomasmylonas.petstore_api_web_app.daos.PetDao;
import com.thomasmylonas.petstore_api_web_app.models.Pet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Controller
@RequestMapping(path = "/")
public class PetController implements WebMvcConfigurer {

    private static final Logger LOGGER = LogManager.getLogger(PetController.class.getName());

    @Autowired
    private PetDao petDao;

    @RequestMapping(path = "pet/{id}",
            method = RequestMethod.GET,
            produces = "application/json")
    public @ResponseBody
    Pet getById(@PathVariable int id) {
        Pet pet = petDao.getOne(id);
        LOGGER.info("The pet with id = " + id + ", is the\n" + pet);
        return pet;
    }

    @RequestMapping(path = "pet/all",
            method = RequestMethod.GET,
            produces = "application/json")
    public @ResponseBody
    List<Pet> getAll() {
        List<Pet> petList = petDao.findAll();
        LOGGER.info("All pets are: \n" + petList);
        return petList;
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

    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
