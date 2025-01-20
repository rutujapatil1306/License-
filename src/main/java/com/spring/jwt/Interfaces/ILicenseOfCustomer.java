package com.spring.jwt.Interfaces;

import com.spring.jwt.dto.CustomerDTO;
import com.spring.jwt.dto.LicenseOfCustomerDTO;

import java.util.List;
import java.util.UUID;

public interface ILicenseOfCustomer {

    CustomerDTO updateStatus(UUID licenseID);
}
