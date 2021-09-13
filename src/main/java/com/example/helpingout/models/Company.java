package com.example.helpingout.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message = "Name required.")
    @Size(min = 3, max = 50, message = "Name must be 3-50 characters.")
    private String name;

    @NotBlank(message = "An email address is required.")
    @Email(message = "Invalid email. Please try again.")
    private String email;

    private Boolean isOrg = true;

    @ManyToMany
    private final List<Tag> tags = new ArrayList<>();

    public Company(int id, String name, String email, Boolean isOrg) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.isOrg = isOrg;
    }

    public Company() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Tag> getTags() {
        return tags;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return id == company.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
