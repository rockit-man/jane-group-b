package com.example.helpingout.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag extends AbstractEntity{

    @Size(min = 2, max = 24, message = "Tags must be between 2-24 characters.")
    @NotBlank
    private String name;

    @ManyToMany(mappedBy = "tags")
    private final List<Profile> profiles = new ArrayList<>();

    public Tag(String name) {
        this.name = name;
    }

    public Tag() {}

    public String getName() {
        return name;
    }

    public String getDisplayName() {return "#" + name + " ";}

    public void setName(String name) {
        this.name = name;
    }

    public List<Profile> getUsers() {
        return profiles;
    }
}
