package com.my.creditadvisoryservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@Entity
@EqualsAndHashCode(of = {"id", "status"})
@Table(name = "application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "money_amount", nullable = false)
    private Long moneyAmount;

    @Column(name = "created_on", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdOn;

    @Column(name = "assigned_on")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime assignedOn;

    @Column(name = "resolved_on")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime resolved_on;

    @ManyToOne(cascade = {CascadeType.PERSIST}, optional = false)
    @JoinColumn(name = "applicant_id", referencedColumnName = "id")
    @JsonIgnore
    private Applicant applicant;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "advisor_id", referencedColumnName = "id")
    @JsonIgnore
    private Advisor advisor;

    public enum Status {
        NEW, ASSIGNED, ON_HOLD, APPROVED, CANCELED, DECLINED
    }

    public void assignToAdvisor(Advisor advisor) {
        advisor.getApplications().add(this);
        this.setAdvisor(advisor);
        this.setStatus(Status.ASSIGNED);
        this.setAssignedOn(LocalDateTime.now());
    }

}
