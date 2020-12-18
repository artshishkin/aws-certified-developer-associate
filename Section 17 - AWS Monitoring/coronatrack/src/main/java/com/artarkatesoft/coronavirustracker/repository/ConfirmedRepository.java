package com.artarkatesoft.coronavirustracker.repository;

import com.artarkatesoft.coronavirustracker.entities.daydata.Confirmed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmedRepository extends JpaRepository<Confirmed, Long> {
}
