package com.example.eksamen2021.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Parish {

    @Id
    private Long parishCode;

    @Column(unique = true, nullable = false)
    private String parishName;
    private Double positivePercentage;
    private Double incidents;
    private Integer totalInfectedLastWeek;
    private LocalDate lockdownStart;
    private Boolean isInLockdown = false;

    @ManyToOne
    @JoinColumn(name = "municipality_id")
    private Municipality municipality;

    public Parish(Long parishCode, String parishName, Double positivePercentage,
                  Double incidents, Integer totalInfectedLastWeek, LocalDate lockdownStart,
                  Boolean isInLockdown, Municipality municipality) {
        this.parishCode = parishCode;
        this.parishName = parishName;
        this.positivePercentage = positivePercentage;
        this.incidents = incidents;
        this.totalInfectedLastWeek = totalInfectedLastWeek;
        this.lockdownStart = lockdownStart;
        this.isInLockdown = isInLockdown;
        this.municipality = municipality;
    }
}
