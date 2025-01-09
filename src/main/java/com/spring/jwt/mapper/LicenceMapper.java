package com.spring.jwt.mapper;

import com.spring.jwt.dto.LicenceDTO;
import com.spring.jwt.entity.Licence;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LicenceMapper {

    public static LicenceDTO toDTO(Licence administrator) {
        LicenceDTO dto = new LicenceDTO();
        dto.setLicenseID(administrator.getLicenseID());
        dto.setLicenseName(administrator.getLicenseName());
        return dto;
    }

    public static Licence toEntity(LicenceDTO dto) {
        Licence administrator = new Licence();
        administrator.setLicenseID(dto.getLicenseID());
        administrator.setLicenseName(dto.getLicenseName());
        return administrator;
    }

    @Bean
    public ModelMapper modelMapper(){
        return  new ModelMapper();
    }
}
