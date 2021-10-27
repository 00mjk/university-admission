package com.solvd.university;

public enum Subject {
    MATHEMATICS("Mathematics"), PHYSICS("Physics"), HISTORY("History"), CHEMISTRY("Chemisttry");

    final String name;

    Subject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
