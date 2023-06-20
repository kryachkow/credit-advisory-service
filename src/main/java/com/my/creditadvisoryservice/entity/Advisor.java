package com.my.creditadvisoryservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "advisor")
@ToString(exclude = {"applications"})
public class Advisor extends SystemUser {

    @Enumerated(EnumType.STRING)
    @Column(name = "advisor_role", nullable = false)
    private AdvisorRole advisorRole;

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "advisor", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Application> applications = new ArrayList<>();


    public Long getMinCreditAmount() {
        return switch (this.advisorRole) {
            case ASSOCIATE -> 0L;
            case PARTNER -> 10000L;
            case SENIOR -> 50001L;
        };
    }

    public Long getMaxCreditAmount() {
        return switch (this.advisorRole) {
            case ASSOCIATE -> 9999L;
            case PARTNER -> 50000L;
            case SENIOR -> Long.MAX_VALUE;
        };
    }


    public boolean applicationCouldBeAssigned() {
        return this.applications.stream().noneMatch(a -> a.getStatus().equals(Application.Status.ASSIGNED));
    }


    public enum AdvisorRole {
        ASSOCIATE, PARTNER, SENIOR
    }

}
