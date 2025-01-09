package com.spring.jwt.Interfaces;

import com.spring.jwt.dto.LicenceDTO;

import java.util.List;

public interface ILicence {

    LicenceDTO saveLicense(LicenceDTO administratorDTO);

    List<LicenceDTO> getAllLicense();
}
