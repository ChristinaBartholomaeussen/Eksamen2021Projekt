package com.example.eksamen2021.data;

import com.example.eksamen2021.model.Municipality;
import com.example.eksamen2021.repository.MunicipalityRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InitData {

    private final MunicipalityRepository municipalityRepository;

    @Bean
    public void insertDefaultData(){
        Municipality aalborg = new Municipality("851", "Aalborg", 0.0F);
        Municipality vejle = new Municipality("630", "Vejle",0.0F);
        Municipality rudersdal = new Municipality("230", "Rudersdal", 0.0F);
        Municipality vesthimmerlands = new Municipality("820","Vesthimmerlands", 0.0F);
        Municipality guldborgsund = new Municipality("376", "Guldborgsund", 0.0F);

        municipalityRepository.save(aalborg);
        municipalityRepository.save(vejle);
        municipalityRepository.save(rudersdal);
        municipalityRepository.save(vesthimmerlands);
        municipalityRepository.save(guldborgsund);
    }
}
