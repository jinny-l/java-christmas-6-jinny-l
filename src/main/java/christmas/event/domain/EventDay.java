package christmas.event.domain;

import java.time.LocalDate;
import java.util.List;

public record EventDay(
        LocalDate date,
        Integer discountAmount,
        Boolean hasStar,
        DayType dayType
) {

    private static final int DATE_INDEX = 0;
    private static final int DISCOUNT_AMOUNT_INDEX = 1;
    private static final int HAS_START_INDEX = 2;

    public static List<EventDay> parseEventDaysFromCSV(List<String> data) {
        return data.stream()
                .map(EventDay::parseEventDay)
                .toList();
    }

    private static EventDay parseEventDay(String line) {
        String[] values = line.split(",");
        return new EventDay(
                LocalDate.parse(values[DATE_INDEX]),
                Integer.parseInt(values[DISCOUNT_AMOUNT_INDEX]),
                Boolean.parseBoolean(values[HAS_START_INDEX]),
                DayType.from(LocalDate.parse(values[DATE_INDEX]))
        );
    }
}
