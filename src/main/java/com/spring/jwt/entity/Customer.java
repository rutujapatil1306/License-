package com.spring.jwt.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID customerId;

    @NotBlank(message = "Name is required.")
    @Size(max = 50, message = "Name must not exceed 50 characters.")
    private String name;

    @NotBlank(message = "Mobile number is required.")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be exactly 10 digits.")
    private String mobileNumber;

    @NotBlank(message = "Email is required.")
    @Email(message = "Email must be a valid format.")
    private String email;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "license_option") // option is reserved keyword in MYSql , so it gets confused
//    private Option option; // NEW_LICENSE , RENEWAL

    @Column(name = "status", nullable = false)
    private String status = "Pending";

    @OneToMany( fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Licence> licence;


}
