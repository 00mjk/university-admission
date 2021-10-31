package com.solvd.university.impl;

import com.solvd.university.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class EnrollmentServiceImpl implements EnrollmentService {

    private List<SpecializationPlan> specializationPlans;

    private List<EntrantForm> entrantForms;

    public EnrollmentServiceImpl(List<SpecializationPlan> specializationPlans) {
        this.specializationPlans = specializationPlans;
    }

    @Override
    public List<SpecializationPlan> getAvailableSpecialisations(SpecialisationType specType) {
        Class<?> specTypeClass;
        switch (specType) {
            case FULL:
                specTypeClass = FullTimeSpecializationPlan.class;
                break;
            case DISTANCE:
                specTypeClass = DistanceSpecializationPlan.class;
                break;
            default:
                specTypeClass = Object.class;
                break;
        }
        return specializationPlans.stream()
                .filter(sp -> specTypeClass.equals(sp.getClass()))
                .collect(Collectors.toList());
    }

    @Override
    public List<SpecializationPlan> getAvailableSpecialisations(@NonNull Integer mark) {
        List<SpecializationPlan> availableSpecByType =
                this.getAvailableSpecialisations(SpecialisationType.FULL);

        return availableSpecByType.stream()
                .filter(sp -> mark.equals(((FullTimeSpecializationPlan) sp).getMinMark()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean canEntrableToHighEducation(Entrable entrable) {
        return entrable.isCanEntrateByAge();
    }
}
