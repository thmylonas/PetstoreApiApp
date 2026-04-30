package com.thomasmylonas.petstore_api_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping(path = "/")
public class HomeController {

    @GetMapping(path = {"/", ""})
    public String homePage() { // "http://localhost:8080"
        return "views/home";
    }

    @GetMapping(path = {"/update-pet"})
    public String updatePet() { // "http://localhost:8080/update-pet"
        return "views/update_pet";
    }
}
