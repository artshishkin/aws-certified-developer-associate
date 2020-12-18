package com.artarkatesoft.coronavirustracker.repository;

import com.artarkatesoft.coronavirustracker.entities.CountrySummaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountrySummaryRepository extends JpaRepository<CountrySummaryEntity, Long> {
    Optional<CountrySummaryEntity> findByCountry(String country);
}
