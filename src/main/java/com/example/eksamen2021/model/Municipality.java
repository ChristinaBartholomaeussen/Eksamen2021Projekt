package com.example.eksamen2021.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Municipality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long municipalityId;
    private String municipalityCode;
    private String municipalityName;
    private Float positivePercentage;

    @OneToMany(mappedBy = "municipality")
    @JsonBackReference
    private Set<Parish> parishes = new HashSet<>();

    public Municipality(String municipalityCode, String municipalityName, Float positivePercentage) {
        this.municipalityCode = municipalityCode;
        this.municipalityName = municipalityName;
        this.positivePercentage = positivePercentage;
    }
}
