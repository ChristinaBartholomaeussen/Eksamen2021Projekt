package com.example.eksamen2021.service;

import com.example.eksamen2021.model.Parish;
import com.example.eksamen2021.repository.ParishRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ParishService {

    private final ParishRepository parishRepository;
    private final MunicipalityService municipalityService;

    public List<Parish> getAllParishes(){
        return parishRepository.findAll();
    }

    public Parish saveParish(Parish parish){
        parishRepository.save(parish);
        municipalityService.updateMunicipality(parish.getMunicipality().getMunicipalityId());
        return parishRepository.save(parish);
    }

    public void deleteParish(Long parishCode){
        parishRepository.deleteById(parishCode);
    }

    public void updateParish(Parish parish){
        parishRepository.save(parish);
        if(parish.getPositivePercentage() >= 3 && parish.getIncidents() >= 600 && parish.getTotalInfectedLastWeek() >= 20){
            parishRepository.updateIsInLockdown(LocalDate.now(), parish.getParishCode());
        }

    }





}
