package com.spring.jwt.service;


import com.spring.jwt.Interfaces.ILicenseList;
import com.spring.jwt.dto.LicenseListDTO;
import com.spring.jwt.entity.LicenseList;
import com.spring.jwt.repository.LicenseListRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LicenseListImpl implements ILicenseList {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LicenseListRepository licenseListRepository;

    @Override
    public LicenseListDTO saveLicense(LicenseListDTO licenseListDTO)
    {
        LicenseList licenseList = modelMapper.map(licenseListDTO, LicenseList.class);
        LicenseList saveLicense = licenseListRepository.save(licenseList);
        return modelMapper.map(licenseList, LicenseListDTO.class);
    }

    @Override
    public List<LicenseListDTO> getAllLicense() {
        List<LicenseList> licenseList = licenseListRepository.findAll();
        List<LicenseListDTO> dtoList = new ArrayList<>();

        for (LicenseList license : licenseList) {
            LicenseListDTO dto = modelMapper.map(license, LicenseListDTO.class);
            dtoList.add(dto);
        }
        return dtoList;
    }



    @Override
    public void deleteLicenseById(UUID licenseListID) {
        LicenseList license = licenseListRepository.findById(licenseListID)
                .orElseThrow(() -> new RuntimeException("License with ID " + licenseListID + "Deleted"));
        licenseListRepository.delete(license);
    }

    @Override
    public LicenseListDTO getLicenseListByID(UUID licenseID) {

        LicenseList licenseList = licenseListRepository.findById(licenseID)
                .orElseThrow(() -> new RuntimeException("License with ID " +licenseID + "Not Found"));

        return modelMapper.map(licenseList, LicenseListDTO.class);

    }

    @Override
    public LicenseListDTO updateLicence(UUID licenseID, LicenseListDTO licenceDTO) {
        LicenseList licence= modelMapper.map(licenceDTO,LicenseList.class);
        LicenseList findedLicence= licenseListRepository.findById(licenseID).orElseThrow(() -> new RuntimeException("Id Not Found Exception"));

        if(licenceDTO.getLicenseName()!=null){
            findedLicence.setLicenseName(licenceDTO.getLicenseName());
        }
        LicenseList UpdatedLicense=licenseListRepository.save(findedLicence);
        return modelMapper.map(UpdatedLicense,LicenseListDTO.class);
    }



}
