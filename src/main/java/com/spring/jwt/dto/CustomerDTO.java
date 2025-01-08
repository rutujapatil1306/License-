package com.spring.jwt.dto;

import com.spring.jwt.entity.Option;

import java.util.UUID;

public class CustomerDTO {

    private UUID customerId;
    private String status = "Pending";
    private Option option;
}
