package org.launchcode.codingevents.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class EventCategory extends AbstractEntity {

    @NotBlank(message="Category name required.")
    @Size(min=3, max=40, message="Category name must be between 3 and 40 characters")
    private String name;

    @OneToMany(mappedBy = "eventCategory") // foreign key is really owned by Event (mappedBy is the name of the field that has the foreign key)
    private final List<Event> events = new ArrayList<>();

    public EventCategory( String name ) {
        this.name = name;
    }

    public EventCategory() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }

    @Override
    public String toString() {
        return name;
    }

}
