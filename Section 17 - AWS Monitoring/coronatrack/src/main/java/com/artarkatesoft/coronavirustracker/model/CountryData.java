package com.artarkatesoft.coronavirustracker.model;

import lombok.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Data
//@NoArgsConstructor
@RequiredArgsConstructor
public class CountryData implements Comparable<CountryData> {

    private final String country;
    private final Long population;
    private final List<PeriodSummary> periodSummaryList;

    @Getter(value = AccessLevel.PRIVATE)
    @Setter(value = AccessLevel.PRIVATE)
    private boolean isMaxCountFound;

    @Setter(value = AccessLevel.PRIVATE)
    private int maxCount;

    @Override
    public int compareTo(CountryData o) {
        if (this.periodSummaryList == null) return 1;
        int thisMaxCount = getMaxCount();
        int otherMaxCount = o.getMaxCount();
        if (thisMaxCount != otherMaxCount) return otherMaxCount - thisMaxCount;
        return this.country.compareTo(o.country);
    }

    public int getMaxCount() {
        if (!isMaxCountFound) {
            maxCount = periodSummaryList.stream()
                    .mapToInt(PeriodSummary::getConfirmedCount).max().orElse(0);
            isMaxCountFound = true;
        }
        return maxCount;
    }

    public CountryData sortDateDescending() {
        List<PeriodSummary> newPeriodSummaryList = this.periodSummaryList.stream().sorted(Comparator.comparing(PeriodSummary::getDate, Comparator.reverseOrder()))
                .collect(Collectors.toList());
        return new CountryData(country, population, newPeriodSummaryList);
    }

    public static class PercentageOfPopulationComparator implements Comparator<CountryData> {
        @Override
        public int compare(CountryData o1, CountryData o2) {
            if (o1.population == null && o2.population == null) return 0;
            if (o1.periodSummaryList == null || o1.population == null) return 1;
            if (o2.periodSummaryList == null || o2.population == null) return -1;
            double delta = (1.0 * o2.getMaxCount() / o2.population) - (1.0 * o1.getMaxCount() / o1.population);
            return (delta == 0) ? 0 : (delta > 0) ? 1 : -1;
//            return (int) (o2.getMaxCount() * o1.population - o1.getMaxCount() * o2.population);
        }
    }
}
