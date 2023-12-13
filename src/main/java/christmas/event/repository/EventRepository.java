package christmas.event.repository;

import christmas.event.domain.ChristmasEvent;
import christmas.event.domain.DecemberGiveawayEvent;
import christmas.event.domain.Event;
import christmas.event.domain.StarDayEvent;
import christmas.event.domain.WeekdayEvent;
import christmas.event.domain.WeekendEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventRepository {

    // 11월, 12월 이벤트 어떻게 나눠서 저장할지 확인
    private static final List<Event> EVENTS = new ArrayList<>();

    static {
        EVENTS.addAll(
                List.of(
                        new ChristmasEvent(),
                        new StarDayEvent("특별 할인"),
                        new WeekdayEvent("평일 할인"),
                        new WeekendEvent("주말 할인"),
                        new DecemberGiveawayEvent()
                )
        );
    }

    public static List<Event> findAll() {
        return Collections.unmodifiableList(EVENTS);
    }
}
