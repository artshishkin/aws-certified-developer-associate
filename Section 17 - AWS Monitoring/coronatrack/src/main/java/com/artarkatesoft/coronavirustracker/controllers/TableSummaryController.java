package com.artarkatesoft.coronavirustracker.controllers;

import com.artarkatesoft.coronavirustracker.dto.ConfirmedPerMillion;
import com.artarkatesoft.coronavirustracker.model.CountryData;
import com.artarkatesoft.coronavirustracker.model.CountryOneParameterData;
import com.artarkatesoft.coronavirustracker.services.CoronaDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class TableSummaryController {

    private final CoronaDataService coronaDataService;


    @Value("${app.analysis.default-countries}")
    private List<String> defaultCountries;

    @GetMapping("/table/week/{countryName}")
    public String getWeekTable(Model model, @PathVariable String countryName) {
        CountryOneParameterData data = coronaDataService.getWeekPeriodSummariesOfCountry(countryName);
        model.addAttribute("data", data);
        return "week-table-country";
    }

    @GetMapping("/table/week")
    public String getWeekTableNeighbors(Model model) {
        List<String> countries = defaultCountries;
        List<CountryOneParameterData> data = countries.stream()
                .map(coronaDataService::getWeekPeriodSummariesOfCountry)
                .sorted()
                .collect(Collectors.toList());
        model.addAttribute("data", data);
        return "period-summary-table";
    }

    @GetMapping("/table/per_million")
    public String getWeekTablePerMillion(
            Model model,
            @RequestParam(value = "min_population", required = false, defaultValue = "0") Integer minPopulation) {
        List<String> countries = coronaDataService.getAllCountries();
        List<CountryOneParameterData> dataList = countries.stream()
                .map(coronaDataService::getWeekPeriodSummariesOfCountry)
                .filter(countryData -> countryData.getPopulation() != null && countryData.getPopulation() != 0)
                .filter(countryData -> countryData.getPopulation() >= minPopulation)
                .sorted(new CountryOneParameterData.PercentageOfPopulationComparator())
                .collect(Collectors.toList());
        List<ConfirmedPerMillion> data = dataList.stream()
                .filter(a -> a.getPopulation() != null && a.getPopulation() != 0)
                .map(a -> ConfirmedPerMillion.builder()
                        .country(a.getCountry())
                        .date(a.getDayOneParameterSummaryList().get(a.getDayOneParameterSummaryList().size() - 1).getDate())
                        .population(a.getPopulation())
                        .confirmed(a.getMaxCount())
                        .confirmedPerMillion(1_000_000.0 * a.getMaxCount() / a.getPopulation())
                        .build()
                )
                .collect(Collectors.toList());

        model.addAttribute("data", data);
        return "summary-per-mln-table";
    }


    @GetMapping("/table/day")
    public String getDayTableNeighbors(Model model) {
        List<String> countries = defaultCountries;
        List<CountryOneParameterData> data = countries.stream()
                .map(coronaDataService::getDayPeriodSummariesOfCountry)
                .sorted()
                .collect(Collectors.toList());
        model.addAttribute("data", data);
        return "period-summary-table";
    }


    @GetMapping({"/table/v2/{period}/{countryName}"})
    public String getV2DayTable(Model model, @PathVariable String period, @PathVariable String countryName) {
        CountryData data;
        switch (period) {
            case "day":
                data = coronaDataService.getDayPeriodSummaryOfCountry(countryName).sortDateDescending();
                break;
            case "week":
                data = coronaDataService.getWeekPeriodSummaryOfCountry(countryName).sortDateDescending();
                break;
            default:
                throw new IllegalArgumentException("Period must be day or week, but found " + period);
        }
        model.addAttribute("countries", coronaDataService.getAllCountries());
        model.addAttribute("data", data);
        model.addAttribute("period", period);
        return "country-full-info-table";
    }

    @GetMapping({"/table/v2/{period}"})
    public String getV2DayTableRequestParam(Model model, @PathVariable String period, @RequestParam String countryName) {
        return getV2DayTable(model, period, countryName);
    }
//    @GetMapping({"/table/v2/{period}"})
//    public String getV2DayTableRequestParam(Model model, @PathVariable String period, @RequestParam String countryName) {
//
//
//        CountryData data;
//        switch (period) {
//            case "day":
//                data = coronaDataService.getDayPeriodSummaryOfCountry(countryName).sortDateDescending();
//                break;
//            case "week":
//                data = coronaDataService.getWeekPeriodSummaryOfCountry(countryName).sortDateDescending();
//                break;
//            default:
//                throw new IllegalArgumentException("Period must be day or week not " + period);
//        }
//        model.addAttribute("countries", coronaDataService.getAllCountries());
//        model.addAttribute("data", data);
//        model.addAttribute("period", period);
//        return "country-full-info-table";
//    }


}
