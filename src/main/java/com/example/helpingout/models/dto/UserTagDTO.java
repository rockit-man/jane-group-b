package com.example.helpingout.models.dto;

import com.example.helpingout.models.Tag;
import com.example.helpingout.models.User;

import javax.validation.constraints.NotNull;

public class UserTagDTO {

    @NotNull
    private User user;

    @NotNull
    private Tag tag;

    public UserTagDTO() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
