package com.spring.jwt.dto;

import com.spring.jwt.entity.Option;

import java.util.List;
import java.util.UUID;

public class CustomerDTO {

    private UUID customerId;
    private String status ;
    private Option option;
    private List<LicenceDTO> licenceDTOS;
}
