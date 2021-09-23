package com.example.helpingout.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class User implements UserDetails {

     @Id
    @GeneratedValue
    private int id;

    private static final long serialVersionUID = 1L;

//    @Id
    @NotBlank(message = "Username is required.")
    @Size(min = 4, max = 20, message = "Username must be 4-20 characters long.")
    private String username;

    private String password;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked;

    @NotBlank(message = "Last name is required.")
    @Size(min = 2, max = 50, message = "Last name must be at least 2 characters long.")
    private String lastname;

    @NotBlank(message = "First name is required.")
    @Size(max = 50, message = "First name must be less than 50 characters long.")
    private String firstname;

    @NotBlank(message = "An email address is required.")
    @Email(message = "Invalid email. Please try again.")
    private String email;

    private Boolean isOrg;

    private Role role;

    @ManyToMany
    private final List<Tag> tags = new ArrayList<>();

    public User(String username, String lastname, String firstname, String email, String password,
                Boolean isOrg, boolean accountNonLocked, Role role) {
        this.username = username;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.isOrg = isOrg;
        this.accountNonLocked = accountNonLocked;
        this.role = role;
    }

    public User() {}


    @Override
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }
    @Override public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override public boolean isEnabled() {
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
    public void setOrg(Boolean org) {
        isOrg = org;
    }
    public List<Tag> getTags() {
        return tags;
    }
    public void addTag(Tag tag) {
        this.tags.add(tag);
    }
    public Role getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    @Override
    public String toString() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
