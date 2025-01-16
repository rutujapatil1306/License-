package com.spring.jwt.service;

import com.spring.jwt.Interfaces.ICustomer;
import com.spring.jwt.dto.CustomerDTO;
import com.spring.jwt.dto.LicenseOfCustomerDTO;
import com.spring.jwt.entity.Customer;
import com.spring.jwt.entity.LicenseOfCustomer;
import com.spring.jwt.repository.CustomerRepository;
import com.spring.jwt.repository.LicenseOfCustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerSerImpl implements ICustomer {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LicenseOfCustomerRepository licenceRepository;

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

//    @Override
//    public CustomerDTO createStatus(UUID customerId,UUID licenceId) {
//        Customer customer=customerRepository.getById(customerId);
//        Licence licence=licenceRepository.getById(licenceId);
//        if(customer != null || licence!=null){
//            customer.setOption(Option.PENDING);
//        }
//        return modelMapper.map(customer,CustomerDTO.class);
//    }

    @Override
    public CustomerDTO assignLicenceAndSetStatus(UUID customerId, UUID licenceId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));

        LicenseOfCustomer licence = licenceRepository.findById(licenceId)
                .orElseThrow(() -> new RuntimeException("Licence not found with ID: " + licenceId));

//        if (licence.getCustomer() != null && !licence.getCustomer().getCustomerId().equals(customerId)) {
//            throw new RuntimeException("Licence is already assigned to another customer.");
//        }

        licence.setCustomer(customer);

        if (customer.getLicence() == null) {
            customer.setLicence(new ArrayList<>());
        }

        boolean licenceExists = false;
        for (LicenseOfCustomer existingLicence : customer.getLicence()) {
            if (existingLicence.getLicenseID().equals(licence.getLicenseID())) {
                licenceExists = true;
                break;
            }
        }

        if (!licenceExists) {
            customer.getLicence().add(licence);
        }

        licenceRepository.save(licence);
        Customer updatedCustomer = customerRepository.save(customer);

        CustomerDTO customerDTO = modelMapper.map(updatedCustomer, CustomerDTO.class);

        List<LicenseOfCustomerDTO> licenceDTOs = new ArrayList<>();
        for (LicenseOfCustomer lic : updatedCustomer.getLicence()) {
            LicenseOfCustomerDTO licenceDTO = modelMapper.map(lic, LicenseOfCustomerDTO.class);
            licenceDTOs.add(licenceDTO);
        }
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
}

//    @Override
//    public CustomerDTO assignLicenceAndSetStatus(UUID customerId, UUID licenceId) {
//        Customer customer = customerRepository.findById(customerId)
//                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));
//
//        Licence licence = licenceRepository.findById(licenceId)
//                .orElseThrow(() -> new RuntimeException("Licence not found with ID: " + licenceId));
//
//        if (customer.getLicence() == null) {
//            customer.setLicence(new ArrayList<>());
//        }
//        customer.getLicence().add(licence);
//        customer.setStatus(Status.PENDING);
//        Customer updatedCustomer = customerRepository.save(customer);
//
//
//        return modelMapper.map(updatedCustomer, CustomerDTO.class);
//    }

