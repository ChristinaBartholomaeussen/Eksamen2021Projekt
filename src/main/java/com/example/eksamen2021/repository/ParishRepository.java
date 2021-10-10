package com.example.eksamen2021.repository;

import com.example.eksamen2021.model.Parish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public interface ParishRepository extends JpaRepository<Parish, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Parish p SET p.isInLockdown = TRUE, " +
            "p.lockdownStart = ?1 " +
            "WHERE p.parishCode = ?2")
    void updateIsInLockdown(LocalDate lockdownStart, Long parishCode);

}
