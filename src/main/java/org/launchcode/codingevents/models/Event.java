package org.launchcode.codingevents.models;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event extends AbstractEntity {

    @NotBlank(message = "Name is required.")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @ManyToOne // establishes "many events to one category" relationship in database (foreign key)
    @NotNull(message = "Category is required")
    private EventCategory eventCategory;

    @ManyToMany // establishes a relationship from an event to every Tag instance placed in its tags collection.
    private final List<Tag> tags = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL) // establishes "one event to one eventDetails" relationship
    @Valid // makes sure that fields inside eventDetails are also valid
    @NotNull
    private EventDetails eventDetails;

    public Event(String name, EventCategory eventCategory) {
        this.name = name;
        this.eventCategory = eventCategory;
    }

    public Event() { // The no-arg constructor needed for th:field
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public EventDetails getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(EventDetails eventDetails) {
        this.eventDetails = eventDetails;
    }

    @Override
    public String toString() {
        return name;
    }

}