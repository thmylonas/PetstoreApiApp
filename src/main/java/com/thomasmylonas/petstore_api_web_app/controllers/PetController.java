package com.thomasmylonas.petstore_api_web_app.controllers;

import com.thomasmylonas.petstore_api_web_app.data_access.daos.PetDao;
import com.thomasmylonas.petstore_api_web_app.exception_handlers.exceptions.InvalidInputSuppliedException;
import com.thomasmylonas.petstore_api_web_app.exception_handlers.exceptions.ResourceNotFoundException;
import com.thomasmylonas.petstore_api_web_app.data_access.entities.Pet;
import com.thomasmylonas.petstore_api_web_app.helpers.UsefulUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/")
public class PetController implements WebMvcConfigurer {

    private static final Logger LOGGER = LogManager.getLogger(PetController.class.getName());

    @Autowired
    private PetDao petDao;

    @RequestMapping(method = RequestMethod.GET)
    public String getHome() {
        return "home";
    }

    @RequestMapping(path = {"pet"}, method = RequestMethod.GET)
    public String getPetPage() {
        return "pet";
    }

    @RequestMapping(path = {
            "pet/find-by-status?status=available",
            "pet/find-by-status?status=sold",
            "pet/find-by-status?status=pending"},
            method = RequestMethod.GET)
    public String getPetFindByStatusPage() {
        return "result_page";
    }

    /*
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
    */

    @RequestMapping(path = "pet/{id}",
            method = RequestMethod.GET,
            produces = "application/json")
    public ResponseEntity<Pet> getById(@PathVariable int id) {

        if (id < 1
            //|| !UsefulUtils.isInteger(String.valueOf(id))
        ) {
            LOGGER.info("400 - Invalid ID supplied");
            throw new InvalidInputSuppliedException("400 - Invalid ID supplied");
        }
        Pet pet = petDao.getOne(id);
        if (pet == null) {
            LOGGER.info("The pet with id = " + id + ", is not found");
            throw new ResourceNotFoundException(id, "The pet with id = %d, is not found");
        }
        LOGGER.info("The pet with id = " + id + ", is the\n" + pet);
        return new ResponseEntity<>(pet, HttpStatus.OK);
    }

    /**
     * http://localhost:8080/PetstoreApiWebApp_war_exploded/pets/find-by-status?status=sold
     *
     * @param status status: available, pending, sold, and all the combinations
     * @return petList filtered by status
     */
    @RequestMapping(path = "pet/findByStatus",
            method = RequestMethod.GET,
            produces = "application/json")
    public ResponseEntity<List<Pet>> findByStatus(@RequestParam(value = "status", defaultValue = "available") String status) {

        String[] statusArray = status.split(",");

        for (String s : statusArray) {
            if (!s.equals("available") && !s.equals("sold") && !s.equals("pending")) {
                LOGGER.info("400 - Invalid status value");
                throw new InvalidInputSuppliedException("400 - Invalid status value");
            }
        }

        List<Pet> petList = petDao.findAll();
        List<Pet> filteredPetList =
                petList.stream()
                        .filter(
                                pet -> {
                                    for (String s : statusArray) {
                                        if (pet.getStatus().toLowerCase().equals(s)) {
                                            return true;
                                        }
                                    }
                                    return false;
                                }
                        )
                        .collect(Collectors.toList());
        LOGGER.info("All pets filtered by status are: \n" + petList);
        return new ResponseEntity<>(filteredPetList, HttpStatus.OK);
    }

    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
