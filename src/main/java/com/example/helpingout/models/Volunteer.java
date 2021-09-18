package com.example.helpingout.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "volunteers")
public class Volunteer {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message = "Username required.")
    @Size(min = 4, max = 20, message = "Username must be 4-20 characters.")
    private String username;

    @NotBlank(message = "Last name is required.")
    @Size(min = 2, max = 50, message = "Last name must be at least 2 characters long.")
    private String lastname;

    @NotBlank(message = "First name is required.")
    @Size(max = 50, message = "First name must be less than 50 characters long.")
    private String firstname;

    @NotBlank(message = "An email address is required.")
    @Email(message = "Invalid email. Please try again.")
    private String email;

    private Boolean isOrg = false;

    @ManyToMany
    private List<Tag> tags = new ArrayList<>();

    public Volunteer(int id, String username, String lastname, String firstname, String email, Boolean isOrg) {
        this.id = id;
        this.username = username;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.isOrg = isOrg;
    }

    public Volunteer() {}

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getOrg() {
        return isOrg;
    }

//    public void setOrg(Boolean org) {
//        isOrg = org;
//    }

    public List<Tag> getTags() {
        return tags;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    @Override
    public String toString() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Volunteer volunteer = (Volunteer) o;
        return id == volunteer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
