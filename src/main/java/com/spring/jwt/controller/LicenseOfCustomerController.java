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

//    @PostMapping("/saveLicense")
//    public ResponseEntity<BaseResponseDTO> saveLicense(@RequestBody LicenseOfCustomerDTO licenseOfCustomerDTO)
//    {
//        try {
//            LicenseOfCustomerDTO licenseOfCustomerDTO1 = iLicenseOfCustomer.saveLicense(licenseOfCustomerDTO);
//            BaseResponseDTO response = new BaseResponseDTO(licenseOfCustomerDTO1,"SUCCESS", "Application Added");
//            return ResponseEntity.status(HttpStatus.CREATED).body(response);
//        }catch (Exception e){
//            BaseResponseDTO errorResponse = new BaseResponseDTO(e.getMessage(),"ERROR", "An error occurred: ");
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
//        }
//    }

    @GetMapping("/getAllLicense")
    public ResponseEntity<BaseResponseDTO> getAllLicense ()
    {
        try{
            List<LicenseOfCustomerDTO> licenseOfCustomerDTOList = iLicenseOfCustomer.getAllLicense();
            BaseResponseDTO response = new BaseResponseDTO(licenseOfCustomerDTOList,"SUCCESS", "List of All License");
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        }catch (Exception e){
            BaseResponseDTO errorResponseDTO = new BaseResponseDTO(e.getMessage(),"ERROR","List of License not Found:");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDTO);
        }

    }

    @GetMapping("/getById")
     public ResponseEntity<BaseResponseDTO> getById(@RequestParam UUID id){
       try{
           LicenseOfCustomerDTO licenseOfCustomerDTO = iLicenseOfCustomer.getById(id);
           BaseResponseDTO response=new BaseResponseDTO(licenseOfCustomerDTO,"Success","Licence get Successfully");
           return ResponseEntity.status(HttpStatus.FOUND).body(response);
       }
       catch(Exception e){
           BaseResponseDTO errorResponseDTO = new BaseResponseDTO(e.getMessage(),"ERROR", "List of License not Found");
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDTO);

       }
    }

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

}
