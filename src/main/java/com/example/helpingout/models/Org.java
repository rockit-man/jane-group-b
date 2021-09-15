package com.example.helpingout.models;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "organizations")
public class Org  {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked;

    private String password;

    @NotBlank(message = "First Name is required.")
    @Size(max = 50, message = "First Name cannot exceed 50 characters.")
    private String firstName;

    @NotBlank(message = "Last Name is required.")
    @Size(min = 2, max = 50, message = "Last Name must be at least 2 characters long.")
    private String lastName;

    @NotBlank(message = "Organization Name is required.")
    @Size(max = 50, message = "Organization Name cannot exceed 50 characters.")
    private String orgName;

    private Boolean isOrg;

    @NotBlank(message = "An email address is required.")
    @Email(message = "Invalid email. Please try again.")
    private String email;

    @ManyToMany
    private final List<Tag> tags = new ArrayList<>();

    public Org(String orgName,
               String firstName,
               String lastName,
               String email,
               Boolean isOrg,
               Boolean accountNonLocked,
               String password) {
        this.orgName = orgName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.isOrg = isOrg;
        this.accountNonLocked = accountNonLocked;
        this.password = password;
    }

    public Org() {}


    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "read");
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

   public boolean isEnabled() {
        return true;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }
    public boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public int getId() {
        return id;
    }

    public String getLastname() {
        return lastName;
    }

    public void setLastname(String lastname) {
        this.lastName = lastname;
    }

    public String getFirstname() {
        return firstName;
    }

    public void setFirstname(String firstname) {
        this.firstName = firstname;
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
        return orgName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Org org = (Org) o;
        return id == org.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
