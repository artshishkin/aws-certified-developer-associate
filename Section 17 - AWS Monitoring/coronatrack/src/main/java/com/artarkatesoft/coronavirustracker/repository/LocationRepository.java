package com.artarkatesoft.coronavirustracker.repository;

import com.artarkatesoft.coronavirustracker.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    //    Optional<Location> findBy(Location location);
    List<Location> findAllByCountryRegion(String countryName);

    //    List<Location> findAllByPopulationIsNull();

    @Query("SELECT DISTINCT l.countryRegion FROM Location l WHERE l.countryRegion IS NOT EMPTY ORDER BY l.countryRegion")
    List<String> findAllCountries();
}
