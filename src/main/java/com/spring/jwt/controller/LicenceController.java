package com.spring.jwt.controller;

import com.spring.jwt.Interfaces.ILicence;
import com.spring.jwt.dto.LicenceDTO;
import com.spring.jwt.utils.BaseResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/administrator")

public class LicenceController {

    @Autowired
    private ILicence iAdministrator;

    @PostMapping("/saveLicense")
    public ResponseEntity<BaseResponseDTO> saveLicense(@RequestBody  LicenceDTO administratorDTO)
    {
        try {
            LicenceDTO administratorDTO1 = iAdministrator.saveLicense(administratorDTO);
            BaseResponseDTO response = new BaseResponseDTO(administratorDTO1,"SUCCESS", "Application Added");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e){
            BaseResponseDTO errorResponse = new BaseResponseDTO(e.getMessage(),"ERROR", "An error occurred: ");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/getAllLicense")
    public ResponseEntity<BaseResponseDTO> getAllLicense ()
    {
        try{
            List<LicenceDTO> administratorDTOList = iAdministrator.getAllLicense();
            BaseResponseDTO response = new BaseResponseDTO(administratorDTOList,"SUCCESS", "List of All License");
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        }catch (Exception e){
            BaseResponseDTO errorResponseDTO = new BaseResponseDTO(e.getMessage(),"ERROR","List of License not Found:");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDTO);
        }

    }

    @GetMapping("/getById")
     public ResponseEntity<BaseResponseDTO> getById(@RequestParam UUID id){
       try{
           LicenceDTO adminstrator=iAdministrator.getById(id);
           BaseResponseDTO response=new BaseResponseDTO(adminstrator,"Success","Licence get Successfully");
           return ResponseEntity.status(HttpStatus.FOUND).body(response);
       }
       catch(Exception e){
           BaseResponseDTO errorResponseDTO = new BaseResponseDTO(e.getMessage(),"ERROR", "List of License not Found");
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDTO);

       }
    }



}
