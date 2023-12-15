package christmas.domain.date;

import java.time.LocalDate;
import java.util.List;

public record EventDate(
        LocalDate localDate,
        int discountValue,
        boolean hasStar,
        DayType dayType
) {

    private static final int LOCAL_DATE_INDEX = 0;
    private static final int DISCOUNT_VALUE_INDEX = 1;
    private static final int HAS_STAR_INDEX = 2;

    public static List<EventDate> parseFromCsvFile(List<String> lines) {
        return lines.stream()
                .map(EventDate::parseFileParsingDomain)
                .toList();
    }

    private static EventDate parseFileParsingDomain(String line) {
        String[] values = line.split(",");
        LocalDate localDate = LocalDate.parse(values[LOCAL_DATE_INDEX]);

        return new EventDate(
                localDate,
                Integer.parseInt(values[DISCOUNT_VALUE_INDEX]),
                Boolean.parseBoolean(values[HAS_STAR_INDEX]),
                DayType.from(localDate)
        );
    }
}
