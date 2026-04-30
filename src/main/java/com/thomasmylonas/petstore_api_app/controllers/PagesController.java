package com.thomasmylonas.petstore_api_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
//@RequestMapping(path = "/")
public class PagesController {

    @GetMapping(path = {"/", ""})
    public String homePage() { // "http://localhost:8080"
        return "views/home";
    }

    @GetMapping(path = {"/update-pet-with-form/{id}"})
    public String updatePetWithForm(@PathVariable(value = "id") Long id) { // "http://localhost:8080/update-pet-with-form/{id}"
        return "views/update_pet_with_form";
    }
}
