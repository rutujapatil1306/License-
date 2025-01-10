package com.spring.jwt.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class LicenceDTO {

    private UUID licenseID;
    private String licenseName;
   // private CustomerDTO customerDTO;
}
