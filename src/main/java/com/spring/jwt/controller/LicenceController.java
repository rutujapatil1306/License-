package com.spring.jwt.controller;

import com.spring.jwt.Interfaces.ILicence;
import com.spring.jwt.dto.LicenceDTO;
import com.spring.jwt.entity.Licence;
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
    public ResponseEntity<BaseResponseDTO> saveLicense(@RequestBody LicenceDTO administratorDTO)
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

    @PatchMapping("/UpdateLicense")
    public ResponseEntity<BaseResponseDTO> updateLicence(@RequestParam UUID licenseID, @RequestBody LicenceDTO licenceDTO){
        try {
            LicenceDTO licenceDTO1 = iAdministrator.updateLicence(licenseID, licenceDTO);
            BaseResponseDTO LicenceUpdate = new BaseResponseDTO(licenceDTO1,"SUCCESS TO UPDATE", "License Update Successfully");
            return ResponseEntity.status(HttpStatus.FOUND).body(LicenceUpdate);
        }catch (Exception e){
            BaseResponseDTO LicenceUpdate= new BaseResponseDTO("Object","ERROR","Failed To Update License");
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(LicenceUpdate);
        }
    }

    public ResponseEntity<BaseResponseDTO> deleteById(@RequestParam UUID licenceId){
        try {
            LicenceDTO licence = iAdministrator.deleteLicence(licenceId);
            BaseResponseDTO baseResponseDTO = new BaseResponseDTO(licence,"SUCCESS", "Success to Delete");
            return ResponseEntity.status(HttpStatus.OK).body(baseResponseDTO);
        }catch (Exception e){
            BaseResponseDTO responseDTO= new BaseResponseDTO(e.getMessage(),"ERROR","Failed To Delete");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);

        }
    }

}
