package com.example.eksamen2021.restcontroller;

import com.example.eksamen2021.model.Parish;
import com.example.eksamen2021.service.ParishService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/parish")
public class ParishRestController {

    private final ParishService parishService;

    @GetMapping( "/all-parishes")
    @ResponseStatus(HttpStatus.OK)
    public List<Parish> getAllParishes(){
        return parishService.getAllParishes();
    }

    @PostMapping("/create-parish")
    @ResponseStatus(HttpStatus.CREATED)
    public Parish createParish(@RequestBody Parish parish){

        return parishService.saveParish(parish);
    }

    @DeleteMapping("/delete-parish/{parishCode}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteParish(@PathVariable Long parishCode){
        parishService.deleteParish(parishCode);
    }

    @PutMapping("/update-parish")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateParish(@RequestBody Parish parish){
        parishService.updateParish(parish);
    }

}
