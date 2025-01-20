package com.spring.jwt.controller;

import com.spring.jwt.Interfaces.ILicenseOfCustomer;
import com.spring.jwt.dto.CustomerDTO;
import com.spring.jwt.dto.LicenseOfCustomerDTO;
import com.spring.jwt.utils.BaseResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/licenseOfCustomerController")

public class LicenseOfCustomerController {

    @Autowired
    private ILicenseOfCustomer iLicenseOfCustomer;

    @PatchMapping("/updateStatus")
    public ResponseEntity<BaseResponseDTO> updateStatus(@RequestParam UUID licenseID){
        try{
            CustomerDTO customerDTO = iLicenseOfCustomer.updateStatus(licenseID);
            BaseResponseDTO response = new BaseResponseDTO(customerDTO,"Success","Status Updated Successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (Exception e){
            BaseResponseDTO errorResponseDTO = new BaseResponseDTO(e.getMessage(),"Error","Status Not updated");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDTO);
        }
    }

    @GetMapping("/byStatus")
    public ResponseEntity<BaseResponseDTO> statusBy(@RequestParam String status) {
        try {
            List<LicenseOfCustomerDTO> co = iLicenseOfCustomer.findByStatus(status);
            BaseResponseDTO bs = new BaseResponseDTO(co, "ALL OK", "By Status Get successfully");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bs);
        } catch (Exception e) {
            BaseResponseDTO errorResponseDTO = new BaseResponseDTO(e.getMessage(), "Error", "Status Not updated");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDTO);
        }
    }
}
