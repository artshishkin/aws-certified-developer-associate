package com.artarkatesoft.coronavirustracker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeriodSummary {
    private LocalDate date;
    private Integer confirmedCount;
    private Integer confirmedDelta;
    private Integer deathsCount;
    private Integer deathsDelta;
    private Integer recoveredCount;
    private Integer recoveredDelta;
}
