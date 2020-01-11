package com.thomasmylonas.petstore_api_web_app.controllers;

import com.thomasmylonas.petstore_api_web_app.data_access.daos.PetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
@RequestMapping(path = "/")
public class PagesController implements WebMvcConfigurer {

    @Autowired
    private PetDao petDao;

    @RequestMapping(method = RequestMethod.GET)
    public String getHome() {
        return "home";
    }

    @RequestMapping(path = {"petHomePage"}, method = RequestMethod.GET)
    public String getPetPage() {
        return "petHomePage";
    }

    @RequestMapping(path = {
            "pet/findByStatus?status=available",
            "pet/findByStatus?status=sold",
            "pet/findByStatus?status=pending"},
            method = RequestMethod.GET)
    public String getPetFindByStatusPage() {
        return "result_page";
    }

    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
