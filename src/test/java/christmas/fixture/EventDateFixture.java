package christmas.fixture;

import christmas.date.domain.DayType;
import christmas.date.domain.EventDate;
import java.time.LocalDate;

public enum EventDateFixture {

    WEEKDAY_NO_STAR(LocalDate.of(23, 12, 4), 1300, false, DayType.WEEKDAY),
    WEEKDAY_HAS_STAR(LocalDate.of(23, 12, 3), 1200, true, DayType.WEEKDAY),
    WEEKEND_NO_STAR(LocalDate.of(23, 12, 1), 1000, false, DayType.WEEKEND),
    AFTER_CHRISTMAS_NO_STAR(LocalDate.of(23, 12, 26), 0, false, DayType.WEEKDAY);

    private final LocalDate date;
    private final Integer discountAmount;
    private final Boolean hasStar;
    private final DayType dayType;

    EventDateFixture(LocalDate date, Integer discountAmount, Boolean hasStar, DayType dayType) {
        this.date = date;
        this.discountAmount = discountAmount;
        this.hasStar = hasStar;
        this.dayType = dayType;
    }

    public EventDate create() {
        return new EventDate(date, discountAmount, hasStar, dayType);
    }
}
