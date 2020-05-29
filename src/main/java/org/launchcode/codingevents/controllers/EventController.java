package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.data.TagRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventCategory;
import org.launchcode.codingevents.models.Tag;
import org.launchcode.codingevents.models.dto.EventTagDTO;
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

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @GetMapping
    public String displayEvents(@RequestParam(required = false) Integer categoryId, Model model) {

        if (categoryId == null) {
            model.addAttribute("title", "All Events");
            model.addAttribute("events", eventRepository.findAll()); // .findAll()
        } else {
            Optional<EventCategory> result = eventCategoryRepository.findById(categoryId);
            if (result.isEmpty()) {
                model.addAttribute("title", "Invalid Category ID: " + categoryId);
            } else {
                EventCategory category = result.get();
                model.addAttribute("title", "Events in Category: " + category.getName());
                model.addAttribute("events", category.getEvents()); // will call getter for the events field based on category id and OneToMany mapping
            }
        }

        return "events/index";
    }

    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        model.addAttribute("event", new Event()); // added once no-arg constructor created
        model.addAttribute("categories", eventCategoryRepository.findAll()); // fetch all event categories
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

    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model, @PathVariable int eventId){
        Optional<Event> eventHolder = eventRepository.findById(eventId);
        Event eventToEdit = eventHolder.get();

        model.addAttribute("event", eventToEdit);
        String title = "Edit Event " + eventToEdit.getName() + " (id=" + eventToEdit.getId() + ")";
        model.addAttribute("title", title );

        return "events/edit";
    }

    @PostMapping("edit")
    public String processEditForm(int eventId, String name, String description) {
        Event eventToEdit = eventRepository.findById(eventId).get();

        eventToEdit.setName(name);
        eventToEdit.getEventDetails().setDescription(description);

        eventRepository.save(eventToEdit); // if the event exists based on not-null id, then a merge will update the table entry

        return "redirect:";
    }

    @GetMapping("details")
    public String displayEventDetails(@RequestParam Integer eventId, Model model) {

        Optional<Event> result = eventRepository.findById(eventId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Event ID: " + eventId);
        } else {
            Event event = result.get();
            model.addAttribute("title", event.getName() + " Details");
            model.addAttribute("event", event);
            model.addAttribute("tags", event.getTags());
        }

        return "events/details";
    }

    // responds to events/add-tag?eventId=3
    @GetMapping("add-tag")
    public String displayAddTagForm(@RequestParam Integer eventId, Model model) {

        Optional<Event> result = eventRepository.findById(eventId);
        Event event = result.get();
        model.addAttribute("title", "Add Tag to: " + event.getName());
        model.addAttribute( "tags", tagRepository.findAll());

        EventTagDTO eventTag = new EventTagDTO();
        eventTag.setEvent(event);

        model.addAttribute("eventTag", eventTag);

        return "events/add-tag";
    }

    @PostMapping("add-tag")
    public String processAddTagForm(@ModelAttribute @Valid EventTagDTO eventTag, Errors errors, Model model) {

        if (!errors.hasErrors()) {

            Event event = eventTag.getEvent();
            Tag tag = eventTag.getTag();

            if (!event.getTags().contains(tag)){
                event.addTag(tag);
                eventRepository.save(event);
            }
            return "redirect:details?eventId=" + event.getId(); //  must use redirect here
        }
        return "redirect:add-tag";
    }
}













