package christmas.plan.date.domain;

import java.time.LocalDate;
import java.util.List;

public record EventDate(
        LocalDate date,
        Integer discountAmount,
        Boolean hasStar,
        DayType dayType
) {

    private static final int DATE_INDEX = 0;
    private static final int DISCOUNT_AMOUNT_INDEX = 1;
    private static final int HAS_START_INDEX = 2;

    public static List<EventDate> parseEventDaysFromCSV(List<String> data) {
        return data.stream()
                .map(EventDate::parseEventDay)
                .toList();
    }

    private static EventDate parseEventDay(String line) {
        String[] values = line.split(",");
        return new EventDate(
                LocalDate.parse(values[DATE_INDEX]),
                Integer.parseInt(values[DISCOUNT_AMOUNT_INDEX]),
                Boolean.parseBoolean(values[HAS_START_INDEX]),
                DayType.from(LocalDate.parse(values[DATE_INDEX]))
        );
    }

    public boolean isSameDay(int dayOfMonth) {
        return date.getDayOfMonth() == dayOfMonth;
    }

    public boolean isWeekend() {
        return dayType == DayType.WEEKEND;
    }

    public boolean isWeekday() {
        return dayType == DayType.WEEKDAY;
    }
}
