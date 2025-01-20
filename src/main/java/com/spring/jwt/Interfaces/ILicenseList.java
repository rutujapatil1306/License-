package com.spring.jwt.Interfaces;

import com.spring.jwt.dto.LicenseListDTO;

import java.util.List;
import java.util.UUID;

public interface ILicenseList {

    LicenseListDTO updateLicence(UUID licenseID, LicenseListDTO licenceDTO) ;

    LicenseListDTO saveLicense(LicenseListDTO licenseListDTO);

    List<LicenseListDTO> getAllLicense();

    void deleteLicenseById(UUID licenseListID);


}
