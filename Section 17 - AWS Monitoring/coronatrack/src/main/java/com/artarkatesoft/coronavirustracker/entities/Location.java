package com.artarkatesoft.coronavirustracker.entities;

import com.artarkatesoft.coronavirustracker.entities.daydata.Confirmed;
import com.artarkatesoft.coronavirustracker.entities.daydata.Deaths;
import com.artarkatesoft.coronavirustracker.entities.daydata.Recovered;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "location", indexes = {@Index(columnList = "countryRegion")})
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String provinceState;

    private String countryRegion;

    @Column(name = "Lon")
    private double longitude;

    @Column(name = "Lat")
    private double latitude;

    @ToString.Exclude
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Confirmed> confirmedList;
    @ToString.Exclude
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Deaths> deathsList;
    @ToString.Exclude
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Recovered> recoveredList;
}
