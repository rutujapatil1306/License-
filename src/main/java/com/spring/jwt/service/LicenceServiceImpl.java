package com.spring.jwt.service;

import com.spring.jwt.Interfaces.ILicence;
import com.spring.jwt.dto.LicenceDTO;
import com.spring.jwt.entity.Licence;
import com.spring.jwt.mapper.LicenceMapper;
import com.spring.jwt.repository.LicenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LicenceServiceImpl implements ILicence {

    @Autowired
    private LicenceRepository administratorRepository;

    @Override
    public LicenceDTO saveLicense(LicenceDTO administratorDTO) {
        Licence administrator = LicenceMapper.toEntity(administratorDTO);
        Licence savedAdministrator = administratorRepository.save(administrator);
        return LicenceMapper.toDTO(savedAdministrator);
    }

    @Override
    public List<LicenceDTO> getAllLicense()
    {
        List<Licence> administrators = administratorRepository.findAll();
        List<LicenceDTO> dtoList = new ArrayList<>();

        for (Licence administrator : administrators) {
            LicenceDTO dto = LicenceMapper.toDTO(administrator);
            dtoList.add(dto);
        }

        return dtoList;
    }
}
