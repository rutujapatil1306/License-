package com.spring.jwt.Interfaces;

import com.spring.jwt.dto.CustomerDTO;

public interface ICustomer {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
}
