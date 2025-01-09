package com.spring.jwt.mapper;

import com.spring.jwt.dto.AdministratorDTO;
import com.spring.jwt.entity.Licence;

public class AdministratorMapper {

    public static AdministratorDTO toDTO(Licence administrator) {
        AdministratorDTO dto = new AdministratorDTO();
        dto.setLicenseID(administrator.getLicenseID());
        dto.setLicenseName(administrator.getLicenseName());
        return dto;
    }

    public static Licence toEntity(AdministratorDTO dto) {
        Licence administrator = new Licence();
        administrator.setLicenseID(dto.getLicenseID());
        administrator.setLicenseName(dto.getLicenseName());
        return administrator;
    }
}
