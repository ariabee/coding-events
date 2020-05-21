package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("eventCategories")
public class EventCategoryController {

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

//    displayAllEvents
//    renderCreateEventCategoryForm
//    processCreateEventCategoryForm

}
