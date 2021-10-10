package com.example.eksamen2021.service;

import com.example.eksamen2021.model.Municipality;
import com.example.eksamen2021.repository.MunicipalityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MunicipalityService {

    private final MunicipalityRepository municipalityRepository;

    public List<Municipality> getAllMunicipality(){
        return municipalityRepository.findAll();
    }
    public void updateMunicipality(Long id){
        municipalityRepository.updateMunicipality(id);
    }
}
