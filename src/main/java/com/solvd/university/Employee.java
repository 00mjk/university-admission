package com.solvd.university;

import java.util.Objects;

public class Employee extends Person {

    private EmployeePosition position;

    public Employee(String surname, String name, EmployeePosition position) throws PersonInvalidDataException {
        super(surname, name);
        this.position = position;
    }

    public Employee(String surname, String name, String patronymic, EmployeePosition position) throws PersonInvalidDataException {
        super(surname, name, patronymic);
        this.position = position;
    }

    public EmployeePosition getPosition() {
        return position;
    }

    public void setPosition(EmployeePosition position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(position, employee.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), position);
    }

    @Override
    public String toString() {
        String prefix = EmployeePosition.MANAGER.equals(this.position) ? "Mr." : "Resp.";
        return String.format(
                "%s %s %s %s - %s", prefix, this.getSurname(), this.getName(), this.getPatronymic(), this.position);
    }

    @Override
    @Deprecated
    public String shortNameFormat() {
        return String.format("%s. - %s", super.shortNameFormat(), this.position);
    }
}
