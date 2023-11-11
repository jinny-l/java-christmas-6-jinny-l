package christmas.plan.event.repository;

import christmas.global.util.io.CSVReader;
import christmas.plan.event.domain.EventDay;
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
