package com.thomasmylonas.petstore_api_web_app.controllers;

import com.thomasmylonas.petstore_api_web_app.data_access.daos.PetDao;
import com.thomasmylonas.petstore_api_web_app.exception_handlers.exceptions.InvalidInputSuppliedException;
import com.thomasmylonas.petstore_api_web_app.exception_handlers.exceptions.ResourceNotFoundException;
import com.thomasmylonas.petstore_api_web_app.data_access.entities.Pet;
import com.thomasmylonas.petstore_api_web_app.helpers.UsefulUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/pet")
public class PetController implements WebMvcConfigurer {

    private static final Logger LOGGER = LogManager.getLogger(PetController.class.getName());

    @Autowired
    private PetDao petDao;

    @RequestMapping(path = "/{id}",
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
     * http://localhost:8080/PetstoreApiWebApp_war_exploded/pet/findByStatus?status=sold
     *
     * @param status status: available, pending, sold, and all the combinations
     * @return petList filtered by status
     */
    @RequestMapping(path = "/findByStatus",
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

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pet> postNewPet(@RequestBody Pet pet, UriComponentsBuilder ucb) {

        if (pet == null) {
//            LOGGER.info("The pet with id = " + id + ", is not found");
//            throw new ResourceNotFoundException(id, "The pet with id = %d, is not found");
        }
        Pet petPersisted = petDao.save(pet);

        HttpHeaders headers = new HttpHeaders();
//        URI locationUri = URI.create("http://localhost:8080/PetstoreApiWebApp_war_exploded/pet/" + petPersisted.getId());
        URI locationUri = ucb.path("/pet")
                .path(String.valueOf(petPersisted.getId()))
                .build().toUri();
        headers.setLocation(locationUri);

        LOGGER.info("The new pet:\n" + pet + "\nis added, with the id = " + petPersisted.getId());
        return new ResponseEntity<>(petPersisted, headers, HttpStatus.CREATED);
    }

    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /*
    @RequestMapping(path = "/{id}",
            method = RequestMethod.GET,
            produces = "application/json")
    public @ResponseBody
    Pet getById(@PathVariable int id) {
        Pet pet = petDao.getOne(id);
        LOGGER.info("The pet with id = " + id + ", is the\n" + pet);
        return pet;
    }
    @RequestMapping(path = "/all",
            method = RequestMethod.GET,
            produces = "application/json")
    public @ResponseBody
    List<Pet> getAll() {
        List<Pet> petList = petDao.findAll();
        LOGGER.info("All pets are: \n" + petList);
        return petList;
    }
    */
}
