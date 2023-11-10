package christmas.event.repository;

import christmas.event.domain.EventDay;
import christmas.global.util.io.CSVReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventDayRepository {

    private static final List<EventDay> EVENT_DAYS = new ArrayList<>();

    static {
        List<String> csvData = CSVReader.read("src/main/resources/event-days.csv");
        EVENT_DAYS.addAll(EventDay.parseEventDaysFromCSV(csvData));
    }

    public Optional<EventDay> findByDayOfMonth(int dayOfMonth) {
        return EVENT_DAYS.stream()
                .filter(eventDay -> eventDay.isSameDay(dayOfMonth))
                .findAny();
    }
}
