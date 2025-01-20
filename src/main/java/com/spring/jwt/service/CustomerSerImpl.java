package com.spring.jwt.service;

import com.spring.jwt.Interfaces.ICustomer;
import com.spring.jwt.dto.CustomerDTO;
import com.spring.jwt.dto.LicenseOfCustomerDTO;
import com.spring.jwt.entity.Customer;
import com.spring.jwt.entity.LicenseList;
import com.spring.jwt.entity.LicenseOfCustomer;
import com.spring.jwt.entity.Status;
import com.spring.jwt.repository.CustomerRepository;
import com.spring.jwt.repository.LicenseListRepository;
import com.spring.jwt.repository.LicenseOfCustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerSerImpl implements ICustomer {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LicenseOfCustomerRepository licenseOfCustomerRepository;

    @Autowired
    private LicenseListRepository licenseListRepository;

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO)
    {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        if (customerRepository.getAllMobileNumbers() != null) {
            for (int i = 0; i < customerRepository.getAllMobileNumbers().size(); i++) {
                if (customer.getMobileNumber().equals(customerRepository.getAllMobileNumbers().get(i))) {
                    throw new RuntimeException("User Already Exist");
                }
            }
        }
        Customer customer1 = customerRepository.save(customer);
        return modelMapper.map(customer1, CustomerDTO.class);
    }


    @Override
    public CustomerDTO assignLicenceAndSetStatus(UUID customerId, UUID licenseID) {
        // Fetch the customer by ID or throw an exception if not found
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));

        // Fetch the license by ID or throw an exception if not found
        LicenseList licenseList = licenseListRepository.findById(licenseID)
                .orElseThrow(() -> new RuntimeException("License not found with ID: " + licenseID));

        // Create a new LicenseOfCustomer entity to associate the license with the customer
        LicenseOfCustomer licenseOfCustomer1 = new LicenseOfCustomer();
        licenseOfCustomer1.setLicense(licenseList); // Set the license reference
        licenseOfCustomer1.setCustomer(customer);  // Associate with the customer
        licenseOfCustomer1.setLicenseName(licenseList.getLicenseName()); // Set license name
        licenseOfCustomer1.setStatus(Status.PENDING); // Set initial status as PENDING

        // Save the LicenseOfCustomer entity
        licenseOfCustomerRepository.save(licenseOfCustomer1);

        // Update the customer's license list if needed
        if (customer.getLicence() == null) {
            customer.setLicence(new ArrayList<>());
        }
        customer.getLicence().add(licenseOfCustomer1);

        // Save the updated customer
        Customer updatedCustomer = customerRepository.save(customer);

        // Map the updated customer entity to a DTO using ModelMapper
        CustomerDTO customerDTO = modelMapper.map(updatedCustomer, CustomerDTO.class);

        // Manually map licenses to LicenseOfCustomerDTO to exclude LicenseList details
        List<LicenseOfCustomerDTO> licenceDTOs = new ArrayList<>();
        for (LicenseOfCustomer lic : updatedCustomer.getLicence()) {
            LicenseOfCustomerDTO licenceDTO = new LicenseOfCustomerDTO();
            licenceDTO.setLicenseOfCustomerId(lic.getLicenseOfCustomerId());
            licenceDTO.setLicenseName(lic.getLicenseName());
            licenceDTO.setStatus(lic.getStatus());
            licenceDTOs.add(licenceDTO);
        }

        // Set the filtered license DTOs in the customer DTO
        customerDTO.setLicenceDTOS(licenceDTOs);

        return customerDTO;
    }

    @Override
    public CustomerDTO getCustomerWithLicenses(UUID customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));

        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);

        List<LicenseOfCustomer> licenceDTOs = new ArrayList<>();
        for (LicenseOfCustomer licence : customer.getLicence()) {
            LicenseOfCustomerDTO licenceDTO = modelMapper.map(licence, LicenseOfCustomerDTO.class);
            licenceDTOs.add(licence);
        }
        customer.setLicence(licenceDTOs);
        return customerDTO;
    }

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();

        List<CustomerDTO> customerDTOs = new ArrayList<>();
        for (Customer customer : customers) {

            CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);

            List<LicenseOfCustomerDTO> licenceDTOs = new ArrayList<>();
            for (LicenseOfCustomer licenseOfCustomer : customer.getLicence()) {
                LicenseOfCustomerDTO licenceDTO = modelMapper.map(licenseOfCustomer, LicenseOfCustomerDTO.class);
                licenceDTOs.add(licenceDTO);
            }

            customerDTO.setLicenceDTOS(licenceDTOs);
            customerDTOs.add(customerDTO);
        }

        return customerDTOs;
    }







}


