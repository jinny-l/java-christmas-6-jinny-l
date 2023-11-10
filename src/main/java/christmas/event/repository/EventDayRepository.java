package christmas.event.repository;

import christmas.event.domain.EventDay;
import christmas.global.util.io.CSVReader;
import java.util.ArrayList;
import java.util.List;

public class EventDayRepository {

    private static final List<EventDay> EVENT_DAYS = new ArrayList<>();

    static {
        List<String> csvData = CSVReader.read("src/main/resources/event-days.csv");
        EVENT_DAYS.addAll(EventDay.parseEventDaysFromCSV(csvData));
    }
}
