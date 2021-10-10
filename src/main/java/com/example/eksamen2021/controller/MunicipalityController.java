package com.example.eksamen2021.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MunicipalityController {

    @GetMapping("/all-municipalities")
    public String getAllMunicipalities(){
        return "municipalities";
    }
}
