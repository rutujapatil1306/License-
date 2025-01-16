package com.spring.jwt.dto;

import com.spring.jwt.entity.Status;
import lombok.Data;

import java.util.UUID;

@Data
public class LicenseOfCustomerDTO {

    private UUID licenseID;
    private String licenseName;
    private Status status;

   // private CustomerDTO customerDTO;
}
