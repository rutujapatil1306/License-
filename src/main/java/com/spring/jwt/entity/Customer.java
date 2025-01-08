package com.spring.jwt.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID customerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "license_option") // option is reserved keyword in MYSql , so it gets confused
    private Option option; // NEW_LICENSE , RENEWAL

    @Column(name = "status", nullable = false)
    private String status = "Pending";

}
