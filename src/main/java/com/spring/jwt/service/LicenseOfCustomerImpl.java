package com.spring.jwt.service;

import com.spring.jwt.Interfaces.ILicenseOfCustomer;
import com.spring.jwt.dto.CustomerDTO;
import com.spring.jwt.dto.LicenseListDTO;
import com.spring.jwt.dto.LicenseOfCustomerDTO;
import com.spring.jwt.entity.Customer;
import com.spring.jwt.entity.LicenseOfCustomer;
import com.spring.jwt.entity.Status;

import com.spring.jwt.repository.CustomerRepository;
import com.spring.jwt.repository.LicenseOfCustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LicenseOfCustomerImpl implements ILicenseOfCustomer {

    @Autowired
    private LicenseOfCustomerRepository licenseOfCustomerRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CustomerDTO updateStatus(UUID licenseOfCustomerId) {

        LicenseOfCustomer licence = licenseOfCustomerRepository.findById(licenseOfCustomerId)
                .orElseThrow(() -> new RuntimeException("Licence not found with ID: " + licenseOfCustomerId));

        if (licence.getStatus() == Status.PENDING) {
            licence.setStatus(Status.ACTIVE);
        } else {
            throw new RuntimeException("Status can only be updated when the current status is PENDING");
        }
        licenseOfCustomerRepository.save(licence);

        Customer customer = licence.getCustomer();

        if (customer == null) {
            throw new RuntimeException("No associated customer found for this licence");
        }

        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);

        List<LicenseOfCustomer> licenceDTOS = new ArrayList<>();
        for (LicenseOfCustomer customerLicence : customer.getLicence()) {
            LicenseOfCustomerDTO licenceDTO = modelMapper.map(customerLicence, LicenseOfCustomerDTO.class);
            licenceDTOS.add(licence);
        }
        customer.setLicence(licenceDTOS);
        return customerDTO;
    }





}

