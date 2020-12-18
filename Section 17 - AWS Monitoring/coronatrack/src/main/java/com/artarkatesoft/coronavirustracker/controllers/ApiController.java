package com.artarkatesoft.coronavirustracker.controllers;

import com.artarkatesoft.coronavirustracker.entities.CountrySummaryEntity;
import com.artarkatesoft.coronavirustracker.model.CountryData;
import com.artarkatesoft.coronavirustracker.model.CountryOneParameterData;
import com.artarkatesoft.coronavirustracker.model.DayOneParameterSummary;
import com.artarkatesoft.coronavirustracker.services.CoronaDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final CoronaDataService coronaDataService;

    @GetMapping("/country/{countryName}")
    public Map<String, List<DayOneParameterSummary>> getAllDataByCountry(@PathVariable("countryName") String countryName) {
        Map<String, List<DayOneParameterSummary>> result = new HashMap<>();
        result.put("confirmed", coronaDataService.getCountryConfirmedHistory(countryName));
        result.put("deaths", coronaDataService.getCountryDeathsHistory(countryName));
        result.put("recovered", coronaDataService.getCountryRecoveredHistory(countryName));
        return result;
    }

    @GetMapping("/country/{countryName}/{dataType}")
    public List<DayOneParameterSummary> getDataByCountry(@PathVariable("countryName") String countryName, @PathVariable("dataType") String dataType) {
        switch (dataType) {
            case "confirmed":
                return coronaDataService.getCountryConfirmedHistory(countryName);
            case "deaths":
                return coronaDataService.getCountryDeathsHistory(countryName);
            case "recovered":
                return coronaDataService.getCountryRecoveredHistory(countryName);
        }
// TODO: 23.03.2020 What we need to return when error
        throw new IllegalArgumentException(dataType + " is not applicable for ");
    }

    @GetMapping("/country/{countryName}/period/{period}")
    public CountryOneParameterData getConfirmedWeekByCountry(
            @PathVariable("countryName") String countryName,
            @PathVariable("period") String period
    ) {
        switch (period) {
            case "week":
                return coronaDataService.getWeekPeriodSummariesOfCountry(countryName);
            case "day":
                return coronaDataService.getDayPeriodSummariesOfCountry(countryName);
        }
        throw new RuntimeException("Not Found");
    }


    @GetMapping({"/countrydata/{period}/{countryName}"})
    public CountryData getV2DayTable(@PathVariable String period, @PathVariable String countryName) {
        CountryData data;
        switch (period) {
            case "day":
                data = coronaDataService.getDayPeriodSummaryOfCountry(countryName).sortDateDescending();
//                data = coronaDataService.getDayPeriodSummaryOfCountry(countryName);
                break;
            case "week":
                data = coronaDataService.getWeekPeriodSummaryOfCountry(countryName).sortDateDescending();
//                data = coronaDataService.getWeekPeriodSummaryOfCountry(countryName);
                break;
            default:
                throw new IllegalArgumentException("Period must be day or week, but found " + period);
        }
        return data;
    }

    @GetMapping({"/countrysummary/{countryName}"})
    public CountrySummaryEntity getCountrySummary(@PathVariable String countryName) {
        return coronaDataService.getCountrySummary(countryName).orElseThrow(IllegalArgumentException::new);
    }

    @GetMapping({"/summary"})
    public List<CountrySummaryEntity> getSummaryList() {
        return coronaDataService.getSummaryList();
    }

    @GetMapping({"/countries"})
    public List<String> getCountries() {
        return coronaDataService.getAllCountries();
    }


}
