package org.launchcode.codingevents.models;

import javax.validation.constraints.*;
import java.util.Objects;

public class Event {

    private int id;
    private static int nextId = 1;

    @NotBlank(message = "Name is required.")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @Size(max = 500, message = "Description too long!")
    private String description;

    @NotBlank(message = "Contact email is required.")
    @Email(message = "Invalid email. Try again.")
    private String contactEmail;

    // Field should not be null or blank
    // Add a field to collect information about where the event will take place.
    @NotBlank(message="Location cannot be left blank.")
    private String location;

    // Make this field only able to be marked as true.
    // Add a field to collect information about whether an attendee must register for the event or not.
    @AssertTrue(message="Registration must be required at this time.")
    private boolean registrationRequired = true;

    // Valid values for this field should be any number over zero.
    // Add a field to collect information about the number of attendees for the event
    @Positive(message="Number of attendees must be one or more.")
    //@Min(1)
    private int numberOfAttendees;

    // Browse the validation annotations to find one to use on another new field of your choosing.

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public int getNumberOfAttendees() {
        return numberOfAttendees;
    }

    public void setNumberOfAttendees(int numberOfAttendees) {
        this.numberOfAttendees = numberOfAttendees;
    }
    public boolean isRegistrationRequired() {
        return registrationRequired;
    }

    public void setRegistrationRequired(boolean registrationRequired) {
        this.registrationRequired = registrationRequired;
    }



    public Event(String name, String description, String contactEmail) {
        this();
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;
    }

    public Event() { // The no-arg constructor needed for th:field and createForm handler
        this.id = nextId;
        nextId++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}