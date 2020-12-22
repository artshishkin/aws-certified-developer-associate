package com.artarkatesoft.coronavirustracker.controllers;

import com.artarkatesoft.coronavirustracker.entities.PopulationWorldBank;
import com.artarkatesoft.coronavirustracker.repository.PopulationWorldBankRepository;
import com.artarkatesoft.coronavirustracker.services.CoronaDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PopulationController {
    private final CoronaDataService coronaDataService;
    private final PopulationWorldBankRepository populationWorldBankRepository;


    @GetMapping("/pop")
    public Object[] compare() {
        Object[] result = new Object[3];

        List<String> allCountriesCoronaData = coronaDataService.getAllCountries();

        List<String> countriesWorldBank = populationWorldBankRepository.findAll()
                .stream()
                .map(PopulationWorldBank::getCountryName)
                .collect(Collectors.toList());

        List<String> allCountriesNoPopulation = new ArrayList<>(allCountriesCoronaData);
//        Collections.copy(allCountriesNoPopulation,allCountriesCoronaData);
        allCountriesNoPopulation.removeAll(countriesWorldBank);
        result[0] = allCountriesNoPopulation;
        List<String> allCountriesNoCoronaData = new ArrayList<>(countriesWorldBank);
        allCountriesNoCoronaData.removeAll(allCountriesCoronaData);
        result[2] = allCountriesNoCoronaData;

        result[1] = allCountriesCoronaData.stream()
                .map(countryName -> {
                    List<PopulationWorldBank> byCountryNameLike = populationWorldBankRepository.findByCountryNameContaining(countryName);
                    if (byCountryNameLike.size() == 1) return "";
                    if (byCountryNameLike.size() == 0) return countryName + " - Not found";
                    for (PopulationWorldBank populationWorldBank : byCountryNameLike) {
                        if (countryName.equals(populationWorldBank.getCountryName()))
                            return "";
                    }
                    return countryName + " - More then 1 result";
                })
                .filter(countryName -> !StringUtils.isEmpty(countryName));

        return result;
    }

}
