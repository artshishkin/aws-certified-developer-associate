package com.artarkatesoft.coronavirustracker.repository;

import com.artarkatesoft.coronavirustracker.entities.DataCsvHash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DataCsvHashRepository extends JpaRepository<DataCsvHash, Long> {
    Optional<DataCsvHash> findByDataUrl(String dataUrl);
}
