package com.spring.jwt.service;

import com.spring.jwt.Interfaces.ICustomer;
import com.spring.jwt.dto.CustomerDTO;
import com.spring.jwt.entity.Customer;
import com.spring.jwt.entity.Licence;
import com.spring.jwt.entity.Option;
import com.spring.jwt.repository.CustomerRepository;
import com.spring.jwt.repository.LicenceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerSerImpl implements ICustomer {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LicenceRepository repository;

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {

        Customer customer = modelMapper.map(customerDTO, Customer.class);
        if (customerRepository.getAllMobileNumbers() != null) {
            for (int i = 0; i < customerRepository.getAllMobileNumbers().size(); i++) {
                if (customer.getMobileNumber() == customerRepository.getAllMobileNumbers().get(i)) {
                    throw new RuntimeException("User Already Exist");
                }
                customer.setOption(Option.NO_STATUS);
            }
        }
        Customer customer1 = customerRepository.save(customer);
        return modelMapper.map(customer1, CustomerDTO.class);
    }

    @Override
    public CustomerDTO createStatus(UUID customerId,UUID licenceId) {
        Customer customer=customerRepository.getById(customerId);
        Licence licence=repository.getById(licenceId);
        if(customer != null || licence!=null){
            customer.setOption(Option.PENDING);
        }
        return modelMapper.map(customer,CustomerDTO.class);

    }


}
