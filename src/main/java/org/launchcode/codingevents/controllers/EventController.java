package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping
    public String displayAllEvents(Model model) {
        model.addAttribute("title", "All Events");
        model.addAttribute("events", eventRepository.findAll()); // .findAll()
        return "events/index";
    }

    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        model.addAttribute("event", new Event()); // added once no-arg constructor created
        model.addAttribute("types", EventType.values()); // pass in array of enum values to create.html
        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) {
        // if errors, redirect user to create form
        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            return "events/create";
        }
        eventRepository.save(newEvent); // .save(new Event())
        return "redirect:";
    }

    @GetMapping("delete")
    public String renderDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Event");
        model.addAttribute("events", eventRepository.findAll()); // .findAll()
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {

        if (eventIds != null) {
            for (int id : eventIds) {
                eventRepository.deleteById(id); // .deleteById(id)
            }
        }

        return "redirect:";
    }

//    @GetMapping("edit/{eventId}")
//    public String displayEditForm(Model model, @PathVariable int eventId){
//        Optional<Event> eventHolder = eventRepository.findById(eventId);
//        Event eventToEdit = eventHolder.get();
//
//        model.addAttribute("event", eventToEdit);
//        String title = "Edit Event " + eventToEdit.getName() + " (id=" + eventToEdit.getId() + ")";
//        model.addAttribute("title", title );
//
//        return "events/edit";
//    }
//
//    @PostMapping("edit")
//    public String processEditForm(int eventId, String name, String description) {
//        Event eventToEdit = eventRepository.findById(eventId).get();
//        eventToEdit.setName(name);
//        eventToEdit.setDescription(description);
//
//        return "redirect:";
//    }

}













