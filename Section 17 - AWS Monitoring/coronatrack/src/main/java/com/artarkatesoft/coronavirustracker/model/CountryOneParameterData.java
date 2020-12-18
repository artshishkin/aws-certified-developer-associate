package com.artarkatesoft.coronavirustracker.model;

import lombok.*;

import java.util.Comparator;
import java.util.List;

@Data
//@NoArgsConstructor
@RequiredArgsConstructor
public class CountryOneParameterData implements Comparable<CountryOneParameterData> {

    private final String country;
    private final Long population;
    private final List<DayOneParameterSummary> dayOneParameterSummaryList;

    @Getter(value = AccessLevel.PRIVATE)
    @Setter(value = AccessLevel.PRIVATE)
    private boolean isMaxCountFound;

    @Setter(value = AccessLevel.PRIVATE)
    private int maxCount;

    @Override
    public int compareTo(CountryOneParameterData o) {
        if (this.dayOneParameterSummaryList == null) return 1;
        int thisMaxCount = getMaxCount();
        int otherMaxCount = o.getMaxCount();
        if (thisMaxCount != otherMaxCount) return otherMaxCount - thisMaxCount;
        return this.country.compareTo(o.country);
    }

    public int getMaxCount() {
        if (!isMaxCountFound) {
            if (dayOneParameterSummaryList == null || dayOneParameterSummaryList.isEmpty())
                maxCount = 0;
            else
                maxCount = dayOneParameterSummaryList.stream()
                        .mapToInt(DayOneParameterSummary::getCount).max().orElse(0);

            isMaxCountFound = true;
        }
        return maxCount;
    }

    public static class PercentageOfPopulationComparator implements Comparator<CountryOneParameterData> {
        @Override
        public int compare(CountryOneParameterData o1, CountryOneParameterData o2) {
            if (o1.population == null && o2.population == null) return 0;
            if (o1.dayOneParameterSummaryList == null || o1.population == null) return 1;
            if (o2.dayOneParameterSummaryList == null || o2.population == null) return -1;
            double delta = (1.0 * o2.getMaxCount() / o2.population) - (1.0 * o1.getMaxCount() / o1.population);
            return (delta == 0) ? 0 : (delta > 0) ? 1 : -1;
//            return (int) (o2.getMaxCount() * o1.population - o1.getMaxCount() * o2.population);
        }
    }
}
