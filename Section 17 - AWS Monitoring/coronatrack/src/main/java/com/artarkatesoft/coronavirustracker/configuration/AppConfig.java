package com.artarkatesoft.coronavirustracker.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

//@Configuration
@ConfigurationProperties(prefix = "app.coronavirus-data")
@Data
public class AppConfig {
    private String confirmedUrl;
    private String deathsUrl;
    private String recoveredUrl;
    private DailyReport dailyReport;
//    private final DailyReport dailyReport = new DailyReport();

    @Data
    public static class DailyReport{
        private String filenamePattern;
        private String url;
    }
}
