package com.example.helpingout.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Tag {

    @Id
    @GeneratedValue
    private int id;

    @Size(min = 2, max = 24, message = "Tags must be between 2-24 characters.")
    @NotBlank
    private String name;

    @ManyToMany(mappedBy = "tags")
    private final List<User> users = new ArrayList<>();

    public Tag(String name) {
        this.name = name;
    }

    public Tag() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {return "#" + name + " ";}

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return id == tag.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
