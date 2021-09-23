package com.example.helpingout.models.dto;

import com.example.helpingout.models.Tag;
import com.example.helpingout.models.Volunteer;

import javax.validation.constraints.NotNull;

public class VolunteerTagDTO {

    @NotNull
    private Volunteer volunteer;

    @NotNull
    private Tag tag;

    public VolunteerTagDTO() {}

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
