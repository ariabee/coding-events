package org.launchcode.codingevents.models;

import java.util.ArrayList;
import java.util.List;

public class EventSearch {

    public static List<Event> findEventsByValue(String value, Iterable<Event> allEvents) {

        String substring = value.toLowerCase();
        List<Event> results = new ArrayList<>();

        for (Event event : allEvents) {
            if (event.getName().toLowerCase().contains(substring)) {
                results.add(event);
            } else if (event.getEventDetails().getDescription().toLowerCase().contains(substring)) {
                results.add(event);
            } else if (event.getEventCategory().getName().toLowerCase().contains(substring)) {
                results.add(event);
            } else if (checkTagsContainValue(substring, event)) {
                results.add(event);
            }
        }

        return results;
    }

    private static boolean checkTagsContainValue(String value, Event event) {
        List<Tag> tags = event.getTags();
        String valNoSpaces = value.replace(" ", "");

        for (Tag tag : tags) {
            if (tag.getName().toLowerCase().contains(valNoSpaces)) {
                return true;
            }
        }
        return false;
    }

}
