package christmas.plan.event.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public enum DayType {

    WEEKDAY(List.of(
            DayOfWeek.SUNDAY,
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY)
    ),
    WEEKEND(List.of(
            DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY)
    );

    private final List<DayOfWeek> daysOfWeek;

    DayType(List<DayOfWeek> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public static DayType from(LocalDate date) {
        return Arrays.stream(DayType.values())
                .filter(dayType -> dayType.daysOfWeek.contains(date.getDayOfWeek()))
                .findAny()
                .orElseThrow();
    }
}
