package com.example.eksamen2021.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ParishController {

    @GetMapping("/")
    public String getAllParishes(){
        return "index";
    }

    @GetMapping("/create-parish")
    public String createParish(){
        return "create_parish";
    }
}
