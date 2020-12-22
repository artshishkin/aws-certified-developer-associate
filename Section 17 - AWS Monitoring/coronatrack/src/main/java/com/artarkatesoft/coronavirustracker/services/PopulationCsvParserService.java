package com.artarkatesoft.coronavirustracker.services;

import com.artarkatesoft.coronavirustracker.entities.PopulationWorldBank;
import com.artarkatesoft.coronavirustracker.repository.PopulationWorldBankRepository;
import com.artarkatesoft.coronavirustracker.services.location.RenameLocation;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Example;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class PopulationCsvParserService {

    @Value("${app.population.url}")
    private String POPULATION_DATA_URL;

    private final PopulationWorldBankRepository populationWorldBankRepository;

    //    @SneakyThrows
    @Async
    public void fetchPopulationData() {
        log.info("Population World Bank - Fetching CSV data started");

        if (populationWorldBankRepository.count() == 0) {
            try (ZipInputStream zipInputStream = new ZipInputStream(new URL(POPULATION_DATA_URL).openStream())) {
//        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream("c:\\Users\\admin\\Downloads\\API_SP.POP.TOTL_DS2_en_csv_v2_866861.zip"));) {

                ZipEntry entry;

                while ((entry = zipInputStream.getNextEntry()) != null) {
                    if (!entry.getName().contains("Metadata")) {
                        try (InputStreamReader inputStreamReader = new InputStreamReader(zipInputStream);
                             CSVParser parserRecords = CSVFormat.DEFAULT.parse(inputStreamReader)) {
                            boolean startFound = false;
                            for (CSVRecord record : parserRecords) {
                                if (startFound) {
                                    String countryName = record.get(0);
                                    String lastNotEmpty = "";
                                    for (String field : record) {
                                        if (!StringUtils.isEmpty(field))
                                            lastNotEmpty = field;
                                    }
                                    long population;
                                    try {
                                        population = Long.parseLong(lastNotEmpty);
                                    } catch (NumberFormatException e) {
                                        continue;
                                    }
                                    PopulationWorldBank populationWorldBank = new PopulationWorldBank(null, countryName, population);
                                    if (!populationWorldBankRepository.exists(Example.of(populationWorldBank)))
                                        populationWorldBankRepository.save(populationWorldBank);

                                } else if ("Country Name".equals(record.get(0)))
                                    startFound = true;
                            }
                        } catch (IOException e) {
                            log.error("Errors in parsing CSV population data file {}", entry.getName(), e);
                        }
                        break;
                    }
                }
            } catch (IOException e) {
                log.error("Errors in downloading or unzipping CSV population data file {}", POPULATION_DATA_URL, e);
            }
        }
        renamePopulationCountryName();
        appendPopulationData();
        log.info("Population World Bank - Fetching CSV  data finished");
    }


    @Value("classpath:population_data/rename.json")
    private Resource renameResource;

    @Value("classpath:population_data/populationset.json")
    private Resource appendResource;

    private final ObjectMapper objectMapper;

    @SneakyThrows
    private void renamePopulationCountryName() {
        List<RenameLocation> renameLocationList = objectMapper.readValue(renameResource.getInputStream(), new TypeReference<List<RenameLocation>>() {
        });

        for (RenameLocation renameLocation : renameLocationList) {
            List<PopulationWorldBank> popLocationList = populationWorldBankRepository.findByCountryName(renameLocation.getPopulationCountryName());
            if (popLocationList.size() == 1) {
                PopulationWorldBank populationWorldBank = popLocationList.get(0);
                populationWorldBank.setCountryName(renameLocation.getLocationCountryName());
                populationWorldBankRepository.save(populationWorldBank);
            } else {
                log.warn("Can't rename {}. Found {}", renameLocation.getPopulationCountryName(), popLocationList);
            }
        }
    }

    @SneakyThrows
    private void appendPopulationData() {
        List<PopulationWorldBank> appendPopulationList = objectMapper.readValue(appendResource.getInputStream(), new TypeReference<List<PopulationWorldBank>>() {
        });
        populationWorldBankRepository.saveAll(appendPopulationList);
    }
}
