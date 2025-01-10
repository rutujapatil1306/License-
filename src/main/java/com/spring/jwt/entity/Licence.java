package com.spring.jwt.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Licence {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID licenseID;

    @Column(nullable = false)
    private String licenseName;

//    @ManyToOne
//    private  Customer customer;

}
