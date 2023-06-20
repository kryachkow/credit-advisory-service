package com.my.creditadvisoryservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Address {

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "address_number", nullable = false)
    private String addressNumber;

    @Column(name = "zip", nullable = false)
    private String zip;

    @Column(name = "apt", nullable = false)
    private String apt;
}
