package com.solvd.university;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class City {
    
    private String name;

    public City(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return String.format("City: %s", this.name);
    }
}
