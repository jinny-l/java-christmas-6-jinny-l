package christmas.date.repository;

import christmas.date.domain.EventDate;
import christmas.global.util.io.CSVReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventDateRepository {

    private static final List<EventDate> EVENT_DATES = new ArrayList<>();

    static {
        List<String> csvData = CSVReader.read("src/main/resources/event-days.csv");
        EVENT_DATES.addAll(EventDate.parseEventDaysFromCSV(csvData));
    }

    public Optional<EventDate> findByDayOfMonth(int dayOfMonth) {
        return EVENT_DATES.stream()
                .filter(eventDay -> eventDay.isSameDay(dayOfMonth))
                .findAny();
    }
}
