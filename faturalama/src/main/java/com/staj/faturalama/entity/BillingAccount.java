package com.staj.faturalama.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
@Table(name="billing_accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class BillingAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "billing_address_id")
    private BillingAddress billingAddress;

    @JsonIgnore
    @OneToMany(mappedBy = "billingAccount")
    private List<Invoice> invoices;
}
