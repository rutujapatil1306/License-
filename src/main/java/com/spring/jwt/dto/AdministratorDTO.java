package com.spring.jwt.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AdministratorDTO {

    private UUID licenseID;
    private String licenseName;
}
