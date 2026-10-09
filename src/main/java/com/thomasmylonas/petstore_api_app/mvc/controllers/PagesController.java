package com.thomasmylonas.petstore_api_app.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

    /**
     * Endpoint:
     * - GET, "http://localhost:8080"
     *
     * @return The "home" page
     */
    @GetMapping(path = {"/", ""})
    public String home() {
        return "home";
    }

    /**
     * Endpoint:
     * - GET, "http://localhost:8080/pets-home"
     *
     * @return The "pets_home" page
     */
    @GetMapping(path = {"/pets-home"})
    public String petsHome() {
        return "pets_home";
    }

    /**
     * Endpoint:
     * - GET, "http://localhost:8080/update-pet-with-form"
     *
     * @return The "update_pet_with_form" page
     */
    @GetMapping(path = {"/update-pet-with-form"})
    public String updatePetWithForm() {
        return "update_pet_with_form";
    }

    /*
    //
     * Endpoint:
     * - GET, "http://localhost:8080/update-pet-with-form/{id}"
     *
     * @param petId The "petId"
     * @param model The "model"
     * @return The "update_pet_with_form" page
     //
    @GetMapping(path = {"/update-pet-with-form/{id}"})
    public String updatePetWithForm(@PathVariable(value = "id")
                                    @PositiveOrZero(message = "The 'id' must be a positive number or 0")
                                    Long petId, Model model) {
        updatePetWithFormViewBean.setId(petId);
        model.addAttribute("updatePetWithFormViewBean", updatePetWithFormViewBean);
        return "update_pet_with_form";
    }
    */
}
