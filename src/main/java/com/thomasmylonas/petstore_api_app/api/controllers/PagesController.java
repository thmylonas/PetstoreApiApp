package com.thomasmylonas.petstore_api_app.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

    @GetMapping(path = {"/", ""})
    public String home() { // "http://localhost:8080"
        return "home";
    }

    @GetMapping(path = {"/pets-home"})
    public String petsHome() { // "http://localhost:8080/pets-home"
        return "pets_home";
    }

    @GetMapping(path = {"/update-pet-with-form"})
    public String updatePetWithForm() { // "http://localhost:8080/update-pet-with-form"
        return "update_pet_with_form";
    }

    /*@GetMapping(path = {"/update-pet-with-form/{id}"})
    public String updatePetWithForm(@PathVariable(value = "id")
                                    @PositiveOrZero(message = "The 'id' must be a positive number or 0")
                                    Long petId, Model model) { // "http://localhost:8080/update-pet-with-form/{id}"
        updatePetWithFormViewBean.setId(petId);
        model.addAttribute("updatePetWithFormViewBean", updatePetWithFormViewBean);
        return "update_pet_with_form";
    }*/
}
