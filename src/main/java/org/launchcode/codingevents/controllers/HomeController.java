package org.launchcode.codingevents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;


@Controller
public class HomeController {

    // GET handler method that returns the name of template index
    @GetMapping
    public String index(Model model) {
        model.addAttribute("title", "Coding Events");
        return "index";
    }
}
