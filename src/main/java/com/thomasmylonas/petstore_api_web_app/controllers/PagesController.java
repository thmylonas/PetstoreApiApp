package com.thomasmylonas.petstore_api_web_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/")
public class PagesController extends BaseController {

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
}
