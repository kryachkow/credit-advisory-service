package com.my.creditadvisoryservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

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
        return advisorRole.minAmount;
    }

    public Long getMaxCreditAmount() {
        return advisorRole.maxAmount;
    }


    public boolean applicationCouldBeAssigned() {
        return this.applications.stream().noneMatch(a -> a.getStatus().equals(Application.Status.ASSIGNED));
    }


    @RequiredArgsConstructor
    @Getter
    public enum AdvisorRole {
        ASSOCIATE(0L, 9999L), PARTNER(1000L, 50000L), SENIOR(50001L, Long.MAX_VALUE);
        private final Long minAmount;
        private final Long maxAmount;
    }

}
