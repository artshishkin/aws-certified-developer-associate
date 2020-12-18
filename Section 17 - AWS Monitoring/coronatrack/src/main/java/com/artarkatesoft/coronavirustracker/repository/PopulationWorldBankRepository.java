package com.artarkatesoft.coronavirustracker.repository;

import com.artarkatesoft.coronavirustracker.entities.PopulationWorldBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PopulationWorldBankRepository extends JpaRepository<PopulationWorldBank,Long> {
    List<PopulationWorldBank> findByCountryNameContaining(String countryName);
    List<PopulationWorldBank> findByCountryName(String countryName);

}
