package com.artarkatesoft.coronavirustracker.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class DatabaseServicesInitializer {

    private final PopulationCsvParserService populationCsvParserService;
    private final CoronaDataCsvParserService coronaDataCsvParserService;

    @PostConstruct
    public void init() {
        populationCsvParserService.fetchPopulationData();
        coronaDataCsvParserService.fetchVirusDataAsync();
    }
}
