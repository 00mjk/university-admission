package com.solvd.university;

import lombok.Getter;

public enum SpecialisationType {
    FULL("full"), DISTANCE("distance");

    @Getter
    private final String lowerStr;

    SpecialisationType(String lowerStr) {
        this.lowerStr = lowerStr;
    }
}
