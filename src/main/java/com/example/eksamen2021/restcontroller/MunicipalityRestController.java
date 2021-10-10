package com.example.eksamen2021.restcontroller;

import com.example.eksamen2021.model.Municipality;
import com.example.eksamen2021.service.MunicipalityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/municipality")
public class MunicipalityRestController {

    private final MunicipalityService municipalityService;

    @GetMapping("/get-all")
    @ResponseStatus(HttpStatus.OK)
    public List<Municipality> getAll(){
        return municipalityService.getAllMunicipality();
    }

}
