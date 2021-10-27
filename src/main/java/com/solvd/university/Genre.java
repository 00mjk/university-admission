package com.solvd.university;

public enum Genre {
    FANTASY("Fantasy is a genre of literature that features magical and supernatural elements that do not exist in the real world."),
    FANTASTIC("The fantastisc is a subgenre of literary works characterized by the ambiguous presentation of seemingly supernatural forces.");

    private final String description;

    Genre(String desctiption) {
        this.description = desctiption;
    }

    public String getDesctiption() {
        return description;
    }
}
