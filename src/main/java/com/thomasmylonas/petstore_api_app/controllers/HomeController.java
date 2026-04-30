package com.thomasmylonas.petstore_api_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
//@RequestMapping(path = "/")
public class HomeController {

    @GetMapping(path = {"/", ""})
    public String homePage() { // "http://localhost:8080"
        return "views/home";
    }

    @GetMapping(path = {"/update-pet/{id}"})
    public String updatePet(@PathVariable(value = "id") Long id) { // "http://localhost:8080/update-pet"
        return "views/update_pet";
    }
}
