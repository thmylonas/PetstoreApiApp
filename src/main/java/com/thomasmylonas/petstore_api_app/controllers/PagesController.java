package com.thomasmylonas.petstore_api_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String updatePetWithForm(Model model) { // "http://localhost:8080/update-pet-with-form"
        return "update_pet_with_form";
    }

    /*@GetMapping(path = {"/update-pet-with-form/{id}"})
    public String updatePetWithForm(@PathVariable(value = "id") Long id, Model model) { // "http://localhost:8080/update-pet-with-form/{id}"
        updatePetWithFormViewBean.setId(id);
        model.addAttribute("updatePetWithFormViewBean", updatePetWithFormViewBean);
        return "update_pet_with_form";
    }*/
}
