package com.spring.jwt.Interfaces;

import com.spring.jwt.dto.CustomerDTO;

import java.util.UUID;

public interface ICustomer {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    CustomerDTO createStatus(UUID customerId,UUID licenceId);
}
