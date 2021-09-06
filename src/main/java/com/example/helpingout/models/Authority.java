package com.example.helpingout.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "authorities")
public class Authority {

    @Id
    @GeneratedValue
    private int id;

    private String authority;

    public Authority (String authority) {
        this.authority = authority;
    }

    public Authority () {}

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority that = (Authority) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
