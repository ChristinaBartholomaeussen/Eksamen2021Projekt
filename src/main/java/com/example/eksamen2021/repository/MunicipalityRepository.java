package com.example.eksamen2021.repository;

import com.example.eksamen2021.model.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MunicipalityRepository extends JpaRepository<Municipality, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE municipality m " +
            "JOIN (select parish.municipality_id, " +
            "sum(parish.positive_percentage) / count(*) as total_positive " +
            "from parish group by parish.municipality_id) as p " +
            "on m.municipality_id = p.municipality_id " +
            "set m.positive_percentage = p.total_positive " +
            "where m.municipality_id = ?1", nativeQuery = true)
    void updateMunicipality(Long id);
}
