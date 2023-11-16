package christmas.fixture;

import christmas.date.domain.EventDate;
import christmas.date.service.EventDateService;
import christmas.global.config.AppConfig;

public enum EventDateFixture {

    디데이O_별X_평일_4일(4),
    디데이O_별O_평일_3일(3),
    디데이O_별X_주말_1일(1),
    디데이X_별X_평일(26),
    디데이X_별O_평일(31),
    디데이X_별X_주말(30);

    private final int dayOfMonth;

    EventDateFixture(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public EventDate create() {
        EventDateService eventDateService = AppConfig.getInstance().eventDateService();
        return eventDateService.findEventDay(dayOfMonth);
    }
}
