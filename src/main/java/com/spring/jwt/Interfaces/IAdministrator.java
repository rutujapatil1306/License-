package com.spring.jwt.Interfaces;

import com.spring.jwt.dto.AdministratorDTO;

import java.util.List;

public interface IAdministrator {

    AdministratorDTO saveLicense(AdministratorDTO administratorDTO);

    List<AdministratorDTO> getAllLicense();
}
