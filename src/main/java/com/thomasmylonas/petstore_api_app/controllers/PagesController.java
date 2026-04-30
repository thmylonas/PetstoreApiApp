package com.thomasmylonas.petstore_api_app.controllers;

import com.thomasmylonas.petstore_api_app.view_layer.UpdatePetWithFormViewBean;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class PagesController {

    private final UpdatePetWithFormViewBean updatePetWithFormViewBean;

    @GetMapping(path = {"/", ""})
    public String homePage() { // "http://localhost:8080"
        return "views/home";
    }

    @GetMapping(path = {"/update-pet-with-form/{id}"})
    public String updatePetWithForm(@PathVariable(value = "id") Long id, Model model) { // "http://localhost:8080/update-pet-with-form/{id}"
        updatePetWithFormViewBean.setId(id);
        model.addAttribute("updatePetWithFormViewBean", updatePetWithFormViewBean);
        return "views/update_pet_with_form";
    }
}
