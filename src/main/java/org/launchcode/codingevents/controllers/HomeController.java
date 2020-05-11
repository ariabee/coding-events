package org.launchcode.codingevents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // GET handler method that returns the name of template index
    @GetMapping
    public String index() {
        return "index";
    }
}
