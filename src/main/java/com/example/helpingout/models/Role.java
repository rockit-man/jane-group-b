package com.example.helpingout.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;

public enum Role implements GrantedAuthority {
    ADMIN ("Admin", Code.ADMIN),
    ORGANIZATION("Organization", Code.ADMIN),
    VOLUNTEER ("Volunteer", Code.USER),
    USER ("User", Code.USER);

    private final String displayName;
    private final String authority;

    Role(String displayName, String authority) {
        this.displayName = displayName;
        this.authority = authority;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public class Code {
        public static final String ADMIN = "ROLE_ADMIN";
        public static final String USER = "ROLE_USER";
    }

//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//    @Column(nullable = false, length = 45)
//    private String name;
//
//    public Role() { }
//
//    public Role(String name) { this.name = name; }
//    public Role(Integer id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//    public Role(Integer id) { this.id = id; }
//
//    @Override
//    public String toString() {
//        return this.name;
//    }
//
//    public Integer getId() { return id; }
//    public void setId(Integer id) { this.id = id; }
//    public String getName() { return name; }
//    public void setName(String name) { this.name = name; }
}