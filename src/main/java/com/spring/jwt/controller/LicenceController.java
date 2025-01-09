package com.spring.jwt.controller;

import com.spring.jwt.Interfaces.ILicence;
import com.spring.jwt.dto.LicenceDTO;
import com.spring.jwt.utils.BaseResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/administrator")

public class LicenceController {

    @Autowired
    private ILicence iAdministrator;

    @PostMapping("/saveLicense")
    public ResponseEntity<BaseResponseDTO> saveLicense(LicenceDTO administratorDTO)
    {
        try {
            LicenceDTO administratorDTO1 = iAdministrator.saveLicense(administratorDTO);
            BaseResponseDTO response = new BaseResponseDTO("SUCCESS", "Application Added");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e){
            BaseResponseDTO errorResponse = new BaseResponseDTO("ERROR", "An error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/getAllLicense")
    public ResponseEntity<BaseResponseDTO> getAllLicense ()
    {
        try{
            List<LicenceDTO> administratorDTOList = iAdministrator.getAllLicense();
            BaseResponseDTO response = new BaseResponseDTO("SUCCESS", "List of All License");
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        }catch (Exception e){
            BaseResponseDTO errorResponseDTO = new BaseResponseDTO("ERROR", "List of License not Found: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDTO);
        }

    }




}
