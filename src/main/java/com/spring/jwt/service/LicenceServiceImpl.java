package com.spring.jwt.service;

import com.spring.jwt.Interfaces.ILicence;
import com.spring.jwt.dto.LicenceDTO;
import com.spring.jwt.entity.Licence;
import com.spring.jwt.mapper.LicenceMapper;
import com.spring.jwt.repository.LicenceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LicenceServiceImpl implements ILicence {

    @Autowired
    private LicenceRepository administratorRepository;

    @Autowired
    private ModelMapper modelMapper;

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

    @Override
    public LicenceDTO getById(UUID id) {
        Licence l=administratorRepository.getById(id);
        LicenceDTO li=modelMapper.map(l,LicenceDTO.class);
        return li;
    }

    @Override
    public LicenceDTO updateLicence(UUID licenseID, LicenceDTO licenceDTO) {
        Licence licence= modelMapper.map(licenceDTO,Licence.class);
        Licence findedLicence= administratorRepository.findById(licenseID).orElseThrow(() -> new RuntimeException("Id Not Found Exception"));

        if(licenceDTO.getLicenseName()!=null){
        licence.setLicenseName(licenceDTO.getLicenseName());
        }
        Licence UpdatedLicense=administratorRepository.save(findedLicence);
        return modelMapper.map(licence,LicenceDTO.class);
    }

    @Override
    public LicenceDTO deleteLicence(UUID licenceId) {
        Licence licence= administratorRepository.findById(licenceId).
                orElseThrow(()-> new RuntimeException("Id Not Found"));
        administratorRepository.delete(licence);
        return null;
    }


}
