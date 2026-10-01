package com.staj.faturalama.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String invoiceNumber;
    private BigDecimal amount;
    private LocalDate invoiceDate;
    private LocalDate dueDate;
    private String filePath;
    private boolean paid;

    @ManyToOne
    @JoinColumn(name = "billing_account_id")
    private BillingAccount billingAccount;
}
