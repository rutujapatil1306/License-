package com.spring.jwt.Interfaces;

import com.spring.jwt.dto.LicenceDTO;

import java.util.List;
import java.util.UUID;

public interface ILicence {

    LicenceDTO saveLicense(LicenceDTO administratorDTO);

    List<LicenceDTO> getAllLicense();

    LicenceDTO getById(UUID id);

    LicenceDTO updateLicence(UUID licenseID, LicenceDTO licenceDTO);

    LicenceDTO deleteLicence(UUID licenceId);
}
