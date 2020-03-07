package com.thomasmylonas.petstore_api_web_app.controllers;

import com.thomasmylonas.petstore_api_web_app.controllers.interface_controllers.PetController;
import com.thomasmylonas.petstore_api_web_app.data_access.daos.PetDao;
import com.thomasmylonas.petstore_api_web_app.data_access.entities.*;
import com.thomasmylonas.petstore_api_web_app.exception_handlers.exceptions.InvalidInputSuppliedException;
import com.thomasmylonas.petstore_api_web_app.exception_handlers.exceptions.ResourceNotFoundException;
import com.thomasmylonas.petstore_api_web_app.models.PetModel;
import com.thomasmylonas.petstore_api_web_app.models.response_status_models.SuccessStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/pet")
public class PetControllerImpl extends BaseController implements PetController<Pet, PetModel> {

    @Autowired
    private PetDao petDao;
    @Autowired
    private SuccessStatus successStatus;

    @RequestMapping(path = "/{id}",
            method = RequestMethod.GET,
            produces = "application/json")
    public ResponseEntity<PetModel> getById(@PathVariable int id) {

        if (id < 1 //|| !UsefulUtils.isInteger(String.valueOf(id))
        ) {
            LOGGER.info("400 - Invalid ID supplied");
            throw new InvalidInputSuppliedException("400 - Invalid ID supplied");
        }
        Pet pet = petDao.findById(id);
        if (pet == null) {
            LOGGER.info("The pet with id: " + id + ", is not found");
            throw new ResourceNotFoundException(id, "The pet with id: %d, is not found");
        }
        LOGGER.info("The pet with id: " + id + ", is the\n" + pet);
        return new ResponseEntity<>(new PetModel(pet), HttpStatus.OK);
    }

    @RequestMapping(path = "/findByStatus",
            method = RequestMethod.GET,
            produces = "application/json")
    public ResponseEntity<List<PetModel>> findByStatus(@RequestParam(value = "status", defaultValue = "available") String status) {

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
                                        if (pet.getStatus().getName().toLowerCase().equals(s)) {
                                            return true;
                                        }
                                    }
                                    return false;
                                }
                        )
                        .collect(Collectors.toList());
        LOGGER.info("All pets filtered by status are: \n" + filteredPetList);

        List<PetModel> filteredPetModelList = new ArrayList<>();
        for (int i = 0; i < filteredPetList.size(); i++) {
            filteredPetModelList.add(new PetModel(filteredPetList.get(i)));
        }
        return new ResponseEntity<>(filteredPetModelList, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pet> save(@RequestBody PetModel petModel, UriComponentsBuilder ucb)
            throws HttpMessageNotReadableException, HttpMediaTypeNotSupportedException {

        Pet pet = new Pet(petModel);
        pet.setPhotoUrls(pet.getPhotoUrls());
        pet.setTags(pet.getTags());
        Pet petPersisted = petDao.save(pet);

        HttpHeaders headers = new HttpHeaders();
//        URI locationUri = URI.create("http://localhost:8080/PetstoreApiWebApp_war_exploded/pet/" + petPersisted.getId());
        URI locationUri = ucb.path("/pet")
                .path(String.valueOf(petPersisted.getId()))
                .build().toUri();
        headers.setLocation(locationUri);

        LOGGER.info("The new pet:\n" + petPersisted + "\nis added, with the id: " + petPersisted.getId());
        return new ResponseEntity<>(petPersisted, headers, HttpStatus.CREATED);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Pet> update(@RequestBody PetModel petModel) {

        Pet updatedPet;
        Integer id = petModel.getId();
        if (id < 1 //|| !UsefulUtils.isInteger(String.valueOf(id))
        ) {
            LOGGER.info("400 - Invalid ID supplied");
            throw new InvalidInputSuppliedException("400 - Invalid ID supplied");
        }

        Pet pet = new Pet(petModel);
        pet.setPhotoUrls(pet.getPhotoUrls());
        pet.setTags(pet.getTags());
        updatedPet = petDao.update(pet);

        if (updatedPet == null) {
            throw new ResourceNotFoundException(0, "404 - Pet not found");
        }
        LOGGER.info("The updated pet is:\n" + new PetModel(updatedPet));
        return new ResponseEntity<>(updatedPet, HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}",
            method = RequestMethod.DELETE,
            produces = "application/json")
    public ResponseEntity<SuccessStatus> delete(@PathVariable int id) {

        if (id < 1 //|| !UsefulUtils.isInteger(String.valueOf(id))
        ) {
            LOGGER.info("400 - Invalid ID supplied");
            throw new InvalidInputSuppliedException("400 - Invalid ID supplied");
        }

        try {
            petDao.deleteById(id);
            String message = String.format("The pet with the id: " + id + ", is deleted", id);
            setResponseStatus(successStatus, null, HttpStatus.OK, message);
            LOGGER.info("The pet with the id: " + id + ", is deleted");
        } catch (ResourceNotFoundException e) {
            LOGGER.info("The pet with id: " + id + ", is not found");
            throw new ResourceNotFoundException(e.getResourceId(), e.getMyMessage());
        }
        return new ResponseEntity<>(successStatus, HttpStatus.OK);
    }

    /*
    @Deprecated
    @RequestMapping(path = "/{id}",
            method = RequestMethod.GET,
            produces = "application/json")
    public @ResponseBody
    Pet getByIdSimpleVersion(@PathVariable int id) {
        Pet pet = petDao.getOne(id);
        LOGGER.info("The pet with id: " + id + ", is the\n" + pet);
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
