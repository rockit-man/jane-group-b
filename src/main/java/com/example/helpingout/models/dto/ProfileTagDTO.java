package com.example.helpingout.models.dto;

import com.example.helpingout.models.Profile;
import com.example.helpingout.models.Tag;

import javax.validation.constraints.NotNull;

public class ProfileTagDTO {

    @NotNull
    private Profile profile;

    @NotNull
    private Tag tag;

    public ProfileTagDTO() {}

    public Profile getUser() {
        return profile;
    }

    public void setUser(Profile profile) {
        this.profile = profile;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
