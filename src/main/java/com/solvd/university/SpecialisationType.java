package com.solvd.university;

import lombok.Getter;

@Getter
public enum SpecialisationType {
    FULL("full"), DISTANCE("distance");

    private final String lowerStr;

    SpecialisationType(String lowerStr) {
        this.lowerStr = lowerStr;
    }
}
