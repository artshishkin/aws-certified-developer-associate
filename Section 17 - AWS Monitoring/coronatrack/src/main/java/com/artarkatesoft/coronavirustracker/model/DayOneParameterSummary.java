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
public class DayOneParameterSummary {
    private LocalDate date;
    private Integer count;
    private Integer dayDelta;
}
