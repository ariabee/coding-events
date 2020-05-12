package org.launchcode.codingevents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

@Controller
@RequestMapping("events")
public class EventController {

    private static HashMap<String, String> events = new HashMap<>();

    @GetMapping
    public String displayAllEvents(Model model) {

        events.put("Taco Party", "A party where we make tacos");
        events.put("Code Things", "An event where we code things.");
        events.put("Comicon", "A cosplay event");

        model.addAttribute("events", events);
        model.addAttribute("title", "All Events");
        return "events/index";
    }

    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        return "events/create";
    }

    @PostMapping("create")
    public String createEvent(@RequestParam String eventName, @RequestParam String description) {
        events.put(eventName, description);
        return "redirect:";
    }

}






//    private static HashMap<String, String> events = new HashMap<>();
//
//    @GetMapping
//    public String displayAllEvents(Model model) {
//
////        HashMap<String, String> events = new HashMap<>();
//
//        events.put("Taco Party","A party where we make tacos.");
//        events.put("Code Things","An event where we code things.");
//        events.put("Nap Time", "The best part of the day. On a cloud.");
//
//        model.addAttribute("events", events);
//        model.addAttribute("title", "All Events");
//        return "events/index";
//    }














