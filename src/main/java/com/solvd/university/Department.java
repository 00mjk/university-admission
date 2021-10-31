package com.solvd.university;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

public class Department {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private List<Specialization> specializations;

    public Department(String name, List<Specialization> specializations) {
        this.name = name;
        this.specializations = specializations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that;
        that = (Department) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name);
        result = 31 * result + specializations.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Department{" + "name='" + name + '\'' +
                ", specializations=" + specializations.toString() +
                '}';
    }
}
