package com.example.helpingout.models;

import java.util.ArrayList;

public class VolunteerData {

    public static ArrayList<Volunteer> findByValue(String value, Iterable<Volunteer> allVolunteers) {
        String lower_val = value.toLowerCase();

        ArrayList<Volunteer> results = new ArrayList<>();

        for (Volunteer volunteer : allVolunteers) {

            if (volunteer.getTags().toString().toLowerCase().contains(lower_val)) {
                results.add(volunteer);
            }
        }
        return results;
    }
}
