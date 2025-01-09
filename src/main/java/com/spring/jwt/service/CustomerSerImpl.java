package com.spring.jwt.service;

import com.spring.jwt.Interfaces.ICustomer;
import com.spring.jwt.dto.CustomerDTO;
import com.spring.jwt.entity.Customer;
import com.spring.jwt.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerSerImpl implements ICustomer {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        if (customerRepository.getAllMobileNumbers() != null) {
            for (int i = 0; i < customerRepository.getAllMobileNumbers().size(); i++) {
                if (customer.getMobileNumber() == customerRepository.getAllMobileNumbers().get(i)) {
                    throw new RuntimeException("User Already Exist");
                }
            }
        }
        Customer customer1 = customerRepository.save(customer);
        return modelMapper.map(customer1, CustomerDTO.class);
    }
}
