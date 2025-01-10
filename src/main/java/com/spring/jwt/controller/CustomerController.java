package com.spring.jwt.controller;

import com.spring.jwt.Interfaces.ICustomer;
import com.spring.jwt.dto.CustomerDTO;
import com.spring.jwt.exception.BaseException;
import com.spring.jwt.utils.BaseResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

    @Autowired
    private ICustomer icustomer;

    @PostMapping("/saveCustomer")
    public ResponseEntity<BaseResponseDTO> saveCustomer(@RequestBody CustomerDTO customerDTO ){
        try{
            CustomerDTO customer=icustomer.saveCustomer(customerDTO);
            BaseResponseDTO responseDTO=new BaseResponseDTO("Success","successfully get this ");
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }
        catch(Exception e){
            BaseResponseDTO errorResponseDTO = new BaseResponseDTO("ERROR", "List of License not Found: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDTO);
        }

    }



    @PostMapping("/creatingLicence")
     public ResponseEntity<BaseResponseDTO> createStatus(@RequestParam UUID customerId,UUID liceneceId){
        try{
            CustomerDTO dto=icustomer.createStatus(customerId,liceneceId);
            BaseResponseDTO responseDTO=new BaseResponseDTO("SUCCESS","successfully go to the status");
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        }
        catch(Exception e){
            BaseResponseDTO errorResponseDTO = new BaseResponseDTO("ERROR", "List of License not Found: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDTO);
                    }
    }

}
