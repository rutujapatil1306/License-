package com.spring.jwt.service;

import com.spring.jwt.Interfaces.IAdministrator;
import com.spring.jwt.dto.AdministratorDTO;
import com.spring.jwt.entity.Administrator;
import com.spring.jwt.mapper.AdministratorMapper;
import com.spring.jwt.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdministratorServiceImpl implements IAdministrator {

    @Autowired
    private AdministratorRepository administratorRepository;

    @Override
    public AdministratorDTO saveLicense(AdministratorDTO administratorDTO) {
        Administrator administrator = AdministratorMapper.toEntity(administratorDTO);
        Administrator savedAdministrator = administratorRepository.save(administrator);
        return AdministratorMapper.toDTO(savedAdministrator);
    }

    @Override
    public List<AdministratorDTO> getAllLicense()
    {
        List<Administrator> administrators = administratorRepository.findAll();
        List<AdministratorDTO> dtoList = new ArrayList<>();

        for (Administrator administrator : administrators) {
            AdministratorDTO dto = AdministratorMapper.toDTO(administrator);
            dtoList.add(dto);
        }

        return dtoList;
    }
}
