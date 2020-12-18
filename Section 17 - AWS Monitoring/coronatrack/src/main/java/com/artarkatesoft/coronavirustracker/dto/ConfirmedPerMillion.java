package com.artarkatesoft.coronavirustracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfirmedPerMillion {
    private String country;
    private LocalDate date;
    private Long population;
    private Integer confirmed;
    private Double confirmedPerMillion;
}
