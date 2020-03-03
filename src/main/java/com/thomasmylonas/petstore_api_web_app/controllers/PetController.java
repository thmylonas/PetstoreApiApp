package com.thomasmylonas.petstore_api_web_app.controllers;

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
public class PetController extends BaseController {

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

    /**
     * http://localhost:8080/PetstoreApiWebApp_war_exploded/pet/findByStatus?status=sold
     *
     * @param status status: available, pending, sold, and all the combinations
     * @return petList filtered by status
     */
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

    /**
     * http://localhost:8080/PetstoreApiWebApp_war_exploded/pet
     * It posts a new Pet and its dependent Entities, and the Exceptions thrown are handled
     * in the "ExceptionsHandlerController"
     *
     * @param petModel
     * @param ucb
     * @return
     * @throws HttpMessageNotReadableException    Thrown when RequestBody is not like "PetModel", but like other models
     * @throws HttpMediaTypeNotSupportedException Thrown runtime, when RequestBody is null or not valid JSON
     */
    @RequestMapping(
            method = RequestMethod.POST,
            consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pet> postNewPet(@RequestBody PetModel petModel, UriComponentsBuilder ucb)
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
    public ResponseEntity<Pet> updatePet(@RequestBody PetModel petModel) { //throws HttpMessageNotReadableException, HttpMediaTypeNotSupportedException

        Pet pet = new Pet(petModel);
        pet.setPhotoUrls(pet.getPhotoUrls());
        pet.setTags(pet.getTags());
        petDao.update(pet);

//        LOGGER.info("The new pet:\n" + petPersisted + "\nis added, with the id: " + petPersisted.getId());
        return new ResponseEntity<>(null, HttpStatus.OK);
        // 400: Invalid ID supplied, 404: Pet not found, 405: Validation exception
    }

    @RequestMapping(path = "/{id}",
            method = RequestMethod.DELETE,
            produces = "application/json")
    public ResponseEntity<SuccessStatus> deletePet(@PathVariable int id) {

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
