package com.example.helpingout.models;

import java.util.ArrayList;

public class VolunteerData {

    public static ArrayList<Volunteer> findByColumnAndValue(String column, String value, Iterable<Volunteer> allVolunteers) {

        ArrayList<Volunteer> results = new ArrayList<>();

        if (value.toLowerCase().equals("all")){
            return (ArrayList<Volunteer>) allVolunteers;
        }

        if (column.equals("all")){
            results = findByValue(value, allVolunteers);
            return results;
        }
        for (Volunteer volunteer : allVolunteers) {

            String aValue = getFieldValue(volunteer, column);

            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                results.add(volunteer);
            }
        }

        return results;
    }

    public static String getFieldValue(Volunteer volunteer, String fieldName){
        String theValue;
        if (fieldName.equals("username")){
            theValue = volunteer.getUsername();
        } else {
            theValue = volunteer.getTags().toString();
        }

        return theValue;
    }

    public static ArrayList<Volunteer> findByValue(String value, Iterable<Volunteer> allVolunteers) {
        String lower_val = value.toLowerCase();

        ArrayList<Volunteer> results = new ArrayList<>();

        for (Volunteer volunteer : allVolunteers) {

            if (volunteer.getUsername().toLowerCase().contains(lower_val)) {
                results.add(volunteer);
            } else if (volunteer.getTags().toString().toLowerCase().contains(lower_val)) {
                results.add(volunteer);
            } else if (volunteer.toString().toLowerCase().contains(lower_val)) {
                results.add(volunteer);
            }

        }

        return results;
    }
}
