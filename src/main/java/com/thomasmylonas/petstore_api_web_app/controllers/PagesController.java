package com.thomasmylonas.petstore_api_web_app.controllers;

import com.thomasmylonas.petstore_api_web_app.controllers._base.AbstractController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/")
public class PagesController extends AbstractController {

    @GetMapping
    public String getHome() {
        return "home";
    }

    @GetMapping(path = {"pet-page"})
    public String getPetPage() {
        return "pet_page";
    }

    @GetMapping(path = {
            "pet/findByStatus?status=available",
            "pet/findByStatus?status=sold",
            "pet/findByStatus?status=pending"})
    public String getPetFindByStatusPage() {
        return "result_page";
    }
}
