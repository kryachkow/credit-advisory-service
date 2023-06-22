package com.my.creditadvisoryservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Embeddable
@Table(name = "phone")
@Data
@EqualsAndHashCode(of = {"phoneNumber", "phoneType"})
public class Phone {

    @Column(name = "number", nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private PhoneType phoneType;


    public enum PhoneType {
        HOME, WORK, MOBILE
    }

}
