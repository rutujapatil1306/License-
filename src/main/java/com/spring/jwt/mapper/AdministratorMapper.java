package com.spring.jwt.mapper;

import com.spring.jwt.dto.AdministratorDTO;
import com.spring.jwt.entity.Administrator;

public class AdministratorMapper {

    public static AdministratorDTO toDTO(Administrator administrator) {
        AdministratorDTO dto = new AdministratorDTO();
        dto.setLicenseID(administrator.getLicenseID());
        dto.setLicenseName(administrator.getLicenseName());
        return dto;
    }

    public static Administrator toEntity(AdministratorDTO dto) {
        Administrator administrator = new Administrator();
        administrator.setLicenseID(dto.getLicenseID());
        administrator.setLicenseName(dto.getLicenseName());
        return administrator;
    }
}
