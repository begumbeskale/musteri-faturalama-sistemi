package com.staj.faturalama.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "billing_addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class BillingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String city;
    private String district;
    private String postalCode;
    private String country;
}
