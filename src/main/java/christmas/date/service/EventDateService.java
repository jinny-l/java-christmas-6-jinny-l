package christmas.date.service;

import christmas.date.domain.EventDate;
import christmas.date.exception.EventDateError;
import christmas.date.repository.EventDateRepository;
import christmas.global.config.AppConfig;
import christmas.global.exception.ChristmasPromotionException;

public class EventDateService {

    private static final EventDateService EVENT_DATE_SERVICE = new EventDateService(
            AppConfig.getInstance().eventDateRepository()
    );

    private final EventDateRepository eventDateRepository;

    private EventDateService(EventDateRepository eventDateRepository) {
        this.eventDateRepository = eventDateRepository;
    }

    public static EventDateService getInstance() {
        return EVENT_DATE_SERVICE;
    }

    public EventDate findEventDay(int dayOfMonth) {
        return eventDateRepository.findByDayOfMonth(dayOfMonth)
                .orElseThrow(() -> new ChristmasPromotionException(EventDateError.INVALID_DAY));
    }
}
