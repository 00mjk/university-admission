package com.solvd.university;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Locale;
import java.util.Objects;

@Getter
@Setter
public abstract class EducationalInstitution implements Abbreviable {

    private String name;
    private Date basedDate;
    private City city;

    public EducationalInstitution(String name) {
        this.name = name;
    }

    public EducationalInstitution(String name, Date basedDate, City city) {
        this.name = name;
        this.basedDate = basedDate;
        this.city = city;
    }

    @Override
    public String getAbbreviation() {
        return this.name.substring(0, 4).toUpperCase(Locale.ROOT);
    }

    public abstract String getEducationalInstitutionType();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EducationalInstitution that = (EducationalInstitution) o;
        return Objects.equals(name, that.name) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, basedDate, city);
    }

    @Override
    public String toString() {
        return String.format(
                "Educational institution:\n\tname - %s\n\tBased in - %s\n\tCity - %s",
                this.name, this.basedDate, this.city);
    }
}
