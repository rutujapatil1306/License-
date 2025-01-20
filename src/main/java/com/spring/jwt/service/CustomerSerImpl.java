package com.spring.jwt.service;

import com.spring.jwt.Interfaces.ICustomer;
import com.spring.jwt.dto.CustomerDTO;
import com.spring.jwt.dto.LicenceDTO;
import com.spring.jwt.entity.Customer;
import com.spring.jwt.entity.Licence;
import com.spring.jwt.entity.Status;
import com.spring.jwt.repository.CustomerRepository;
import com.spring.jwt.repository.LicenceRepository;
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
    private LicenceRepository licenceRepository;

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {

        Customer customer = modelMapper.map(customerDTO, Customer.class);
        if (customerRepository.getAllMobileNumbers() != null) {
            for (int i = 0; i < customerRepository.getAllMobileNumbers().size(); i++) {
                if (customer.getMobileNumber() .equals(customerRepository.getAllMobileNumbers().get(i))) {
                    throw new RuntimeException("User Already Exist");
                }
                customer.setStatus(Status.NO_STATUS);
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
    public CustomerDTO getCustomerWithLicenses(UUID customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));

        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);

        List<LicenceDTO> licenceDTOs = new ArrayList<>();
        for (Licence licence : customer.getLicence()) {
            LicenceDTO licenceDTO = modelMapper.map(licence, LicenceDTO.class);
            licenceDTOs.add(licenceDTO);
        }
        customerDTO.setLicence(licenceDTOs);
        return customerDTO;
    }

    @Override
    public CustomerDTO assignLicenceAndSetStatus(UUID customerId, UUID licenceId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));

        Licence licence = licenceRepository.findById(licenceId)
                .orElseThrow(() -> new RuntimeException("Licence not found with ID: " + licenceId));

        if (customer.getLicence() == null) {
            customer.setLicence(new ArrayList<>());
        }
        customer.getLicence().add(licence);
        customer.setStatus(Status.PENDING);
        Customer updatedCustomer = customerRepository.save(customer);
        return modelMapper.map(updatedCustomer, CustomerDTO.class);
    }


    @Override
    public List<CustomerDTO> searchCustomerByName(String name) {
        List<Customer> foundCustomers = customerRepository.findByNameContainingIgnoreCaseOrderByNameAsc(name);
        System.out.println(foundCustomers.size());
        // Convert List<Customer> to List<CustomerDTO> using ModelMapper
        List<CustomerDTO> customerDTOs = new ArrayList<>();
        for (Customer customer : foundCustomers) {
            CustomerDTO dto = modelMapper.map(customer, CustomerDTO.class);
            customerDTOs.add(dto);
        }

        return customerDTOs; // Return the list of DTOs
    }



}
