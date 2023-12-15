package christmas.domain.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public enum DayType {

    WEEKDAY(
            List.of(
                    DayOfWeek.SUNDAY,
                    DayOfWeek.MONDAY,
                    DayOfWeek.TUESDAY,
                    DayOfWeek.WEDNESDAY,
                    DayOfWeek.THURSDAY
            )
    ),
    WEEKEND(
            List.of(
                    DayOfWeek.FRIDAY,
                    DayOfWeek.SATURDAY
            )
    );

    private final List<DayOfWeek> days;

    DayType(List<DayOfWeek> days) {
        this.days = days;
    }

    public static DayType from(LocalDate localDate) {
        return Arrays.stream(DayType.values())
                .filter(dayType -> dayType.days.contains(localDate.getDayOfWeek()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("내부 오류"));
    }
}
