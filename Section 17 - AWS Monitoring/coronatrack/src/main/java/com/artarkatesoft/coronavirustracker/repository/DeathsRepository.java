package com.artarkatesoft.coronavirustracker.repository;

import com.artarkatesoft.coronavirustracker.entities.daydata.Deaths;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeathsRepository extends JpaRepository<Deaths,Long> {
}
