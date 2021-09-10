package com.example.helpingout.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "authorities")
public class Authorities {

    @Id
    @GeneratedValue
    private int id;

    private String authority;

    public Authorities(String authority) {
        this.authority = authority;
    }

    public Authorities() {}

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authorities that = (Authorities) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
