package com.solvd.university;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class CentralizeTestingCertificate extends Certificate {

    private Subject subject;

    public CentralizeTestingCertificate(Integer id, Subject subject, Integer mark) {
        super(id, mark);
        this.subject = subject;
    }

    public CentralizeTestingCertificate(Integer id, Subject subject, Integer mark, LocalDateTime issuedAt) {
        super(id, mark, issuedAt);
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subject);
    }

    @Override
    public String toString() {
        return String.format(
                "%s\n\ttype - Centralize Testing\n\tsuject - %s", super.toString(), this.subject);
    }

    @Override
    public String getCertificateType() {
        String resultString;
        switch (subject) {
            case PHYSICS:
                resultString = "Tech. Centralize Testing Certificate";
                break;
            case HISTORY:
                resultString = "Humanit. Centralize Testing Certificate";
                break;
            default:
                resultString = "Centralize Testing Certificate";
                break;
        }
        return resultString;
    }
}
