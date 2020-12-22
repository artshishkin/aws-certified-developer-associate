package com.artarkatesoft.coronavirustracker.controllers;

import com.artarkatesoft.coronavirustracker.model.CountryOneParameterData;
import com.artarkatesoft.coronavirustracker.model.DayOneParameterSummary;
import com.artarkatesoft.coronavirustracker.repository.PopulationWorldBankRepository;
import com.artarkatesoft.coronavirustracker.services.CoronaDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ChartController {

    private final CoronaDataService coronaDataService;

    // TODO: 20.03.2020 move this repo to service layer
    private final PopulationWorldBankRepository populationWorldBankRepository;

    @GetMapping("/chart")
    public String getChartOnePage(Model model, @RequestParam(value = "country", defaultValue = "Ukraine") String country) {
        model.addAttribute("country", country);
        return "chart1";
    }

    @GetMapping("/chart-new")
    public String getChartPage(Model model, @RequestParam(value = "countries", required = false) String countries) {
        List<String> countryList;
        if (countries == null)
            countryList = Collections.singletonList("Ukraine");
        else
            countryList = Arrays.asList(countries.split(";"));

        Object[][] data = null;

        for (int countryIndex = 0; countryIndex < countryList.size(); countryIndex++) {
            String country = countryList.get(countryIndex);
            CountryOneParameterData countryOneParameterData = coronaDataService.getDayPeriodSummariesOfCountry(country);
            List<DayOneParameterSummary> dayOneParameterSummaryList = countryOneParameterData.getDayOneParameterSummaryList();

            if (data == null) {
                int countrySize = countryList.size();
                int summarySize = dayOneParameterSummaryList.size();
                data = new Object[summarySize + 1][countrySize + 1];

                data[0][0] = "Date";
            }
            data[0][countryIndex + 1] = country;

            for (int summaryIndex = 0; summaryIndex < dayOneParameterSummaryList.size(); summaryIndex++) {
                DayOneParameterSummary dayOneParameterSummary = dayOneParameterSummaryList.get(summaryIndex);
                LocalDate date = dayOneParameterSummary.getDate();
                data[summaryIndex + 1][0] = date;
                Integer count = dayOneParameterSummary.getCount();
                data[summaryIndex + 1][countryIndex + 1] = count;
            }
        }
        model.addAttribute("data", data);
        return "chart2";
    }

    @GetMapping("/chart-all")
    public String getChartAllPage(Model model, @RequestParam(value = "countries", required = false) String countries) {

        List<String> countryList;
        if (countries == null) {
//            countryList = Collections.singletonList("Ukraine");
            countryList = coronaDataService.getAllCountries();
        } else
            countryList = Arrays.asList(countries.split(";"));

        Object[][] data = null;

        List<CountryOneParameterData> sortedDataList = countryList.stream()
                .map(coronaDataService::getDayPeriodSummariesOfCountry)
                .sorted()
                .collect(Collectors.toList());

        for (int countryIndex = 0; countryIndex < sortedDataList.size(); countryIndex++) {
            CountryOneParameterData countryOneParameterData = sortedDataList.get(countryIndex);

            String country = countryOneParameterData.getCountry();
            List<DayOneParameterSummary> dayOneParameterSummaryList = countryOneParameterData.getDayOneParameterSummaryList();


            if (data == null) {
                int countrySize = sortedDataList.size();
                int summarySize = dayOneParameterSummaryList.size();
                data = new Object[summarySize + 1][countrySize + 1];

                data[0][0] = "Date";
            }
            data[0][countryIndex + 1] = country;

            if (dayOneParameterSummaryList == null || dayOneParameterSummaryList.isEmpty()) continue;
            for (int summaryIndex = 0; summaryIndex < dayOneParameterSummaryList.size(); summaryIndex++) {
                DayOneParameterSummary dayOneParameterSummary = dayOneParameterSummaryList.get(summaryIndex);
                LocalDate date = dayOneParameterSummary.getDate();
                data[summaryIndex + 1][0] = date;
                Integer count = dayOneParameterSummary.getCount();
                data[summaryIndex + 1][countryIndex + 1] = count;
            }

        }

        setNullElementsToZero(data);
        model.addAttribute("data", data);
        return "chart2";
    }

    private void setNullElementsToZero(Object[][] data) {
        assert data != null;
        for (int i = 0; i < data.length; i++) {
            Object[] row = data[i];
            for (int i1 = 0; i1 < row.length; i1++) {
                if (row[i1] == null)
                    data[i][i1] = 0;
            }
        }
    }

    @GetMapping("/chart_per_mln")
    public String getChartConfirmedInMlnPage(Model model, @RequestParam(value = "countries", required = false) String countries) {

        List<String> countryList;
        if (countries == null) {
//            countryList = Collections.singletonList("Ukraine");
            countryList = coronaDataService.getAllCountries();
        } else
            countryList = Arrays.asList(countries.split(";"));

        Object[][] data = null;
        CountryOneParameterData.PercentageOfPopulationComparator percentageOfPopulationComparator =
                new CountryOneParameterData.PercentageOfPopulationComparator();
        List<CountryOneParameterData> sortedDataList = countryList.stream()
                .map(coronaDataService::getDayPeriodSummariesOfCountry)
                .sorted(percentageOfPopulationComparator)
                .collect(Collectors.toList());
// TODO: 20.03.2020 if name of country is wrong
        for (int countryIndex = 0; countryIndex < sortedDataList.size(); countryIndex++) {
            CountryOneParameterData countryOneParameterData = sortedDataList.get(countryIndex);

            String country = countryOneParameterData.getCountry();
            List<DayOneParameterSummary> dayOneParameterSummaryList = countryOneParameterData.getDayOneParameterSummaryList();

            Long population = countryOneParameterData.getPopulation();

            if (data == null) {
                int countrySize = sortedDataList.size();
                int summarySize = dayOneParameterSummaryList.size();
                data = new Object[summarySize + 1][countrySize + 1];

                data[0][0] = "Date";
            }
            data[0][countryIndex + 1] = country;

            if (dayOneParameterSummaryList==null || dayOneParameterSummaryList.isEmpty()) continue;
            for (int summaryIndex = 0; summaryIndex < dayOneParameterSummaryList.size(); summaryIndex++) {
                DayOneParameterSummary dayOneParameterSummary = dayOneParameterSummaryList.get(summaryIndex);
                LocalDate date = dayOneParameterSummary.getDate();
                data[summaryIndex + 1][0] = date;
                Integer count = dayOneParameterSummary.getCount();
//                data[summaryIndex + 1][countryIndex + 1] = (population == null) ? 10L*count : 1_000_000_000L * count / population;
                data[summaryIndex + 1][countryIndex + 1] = (population == null) ? 0.1 * count : 1000000.0 * count / population;
            }
        }
        setNullElementsToZero(data);
        model.addAttribute("data", data);
        return "chart2";
    }
}
