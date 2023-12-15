package christmas.repository;

import christmas.domain.date.EventDate;
import christmas.util.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EventDateRepository {

    public static final List<EventDate> EVENT_DATES = new ArrayList<>();

    private EventDateRepository() {
    }

    static {
        List<String> lines = FileReader.read("src/main/resources/dates.csv");

        EVENT_DATES.addAll(
                EventDate.parseFromCsvFile(lines)
        );
    }

    public static EventDate findBy(int day) {
        return EVENT_DATES.stream()
                .filter(eventDate -> Objects.equals(eventDate.localDate().getDayOfMonth(), day))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요."));
    }
}
