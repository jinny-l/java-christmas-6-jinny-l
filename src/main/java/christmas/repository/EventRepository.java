package christmas.repository;

import christmas.domain.event.ChristmasDiscountEvent;
import christmas.domain.event.ChristmasGiveawayEvent;
import christmas.domain.event.Event;
import christmas.domain.event.StarDayDiscountEvent;
import christmas.domain.event.WeekdayDiscountEvent;
import christmas.domain.event.WeekendDiscountEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventRepository {

    private static final List<Event> EVENTS = new ArrayList<>();

    private EventRepository() {
    }

    static {
        EVENTS.addAll(List.of(
                        new ChristmasDiscountEvent(),
                        new StarDayDiscountEvent(),
                        new WeekdayDiscountEvent(),
                        new WeekendDiscountEvent(),
                        new ChristmasGiveawayEvent()
                )
        );
    }

    public static List<Event> findAll() {
        return Collections.unmodifiableList(EVENTS);
    }
}
