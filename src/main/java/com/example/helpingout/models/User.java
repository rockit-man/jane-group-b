package com.example.helpingout.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class User extends AbstractEntity{

    @NotBlank(message = "Username is required.")
    @Size(min = 4, max = 12, message = "Username must be 4-12 characters long.")
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

    @NotBlank(message = "A password is required.")
    @Size(min = 4, max = 12, message = "Passwords must be 4-12 characters long.")
    private String password;

    private String confirmPassword;

    private Boolean isOrg;

    @ManyToMany
    private final List<Tag> tags = new ArrayList<>();

    public User(String username, String lastname, String firstname, String email, String password, String confirmPassword, Boolean isOrg) {
        this.username = username;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.isOrg = isOrg;
    }

    public User() {}

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Boolean getOrg() {
        return isOrg;
    }

    public void setOrg(Boolean org) {
        isOrg = org;
    }

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
}
