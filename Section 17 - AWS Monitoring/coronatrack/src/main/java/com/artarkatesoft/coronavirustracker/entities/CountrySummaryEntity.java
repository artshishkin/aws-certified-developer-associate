package com.artarkatesoft.coronavirustracker.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "country_summary",
        uniqueConstraints = {@UniqueConstraint(columnNames = "country")},
        indexes = {@Index(columnList = "country")})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountrySummaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;
    private Long population;
    private LocalDate date;
    private Integer confirmedCount;
    private Integer confirmedDelta;
    private Integer recoveredCount;
    private Integer recoveredDelta;
    //    private Integer recoveredPerConfirmed;
    private Integer deathsCount;
    private Integer deathsDelta;
//    private Integer deathsPerConfirmed;
//    private Integer deathsPerMillion;
//    private String recoveredDeathsRatio;
//    private Integer activeCount;
//    private Integer activePerMillion;

}
