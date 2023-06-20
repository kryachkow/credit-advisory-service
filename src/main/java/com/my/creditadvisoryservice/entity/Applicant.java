package com.my.creditadvisoryservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
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

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "applicant")
@ToString(exclude = {"applications"})
public class Applicant extends SystemUser {

    @Column(name = "social_security_number", nullable = false, unique = true)
    private String socialSecurityNumber;

    @Embedded
    private Address address;

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "applicant", fetch = FetchType.LAZY)
    private List<Phone> phones = new ArrayList<>();

    @OneToMany(mappedBy = "applicant", fetch = FetchType.LAZY)
    @JsonIgnore
    public List<Application> applications = new ArrayList<>();


}
