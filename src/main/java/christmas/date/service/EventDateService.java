package christmas.date.service;

import christmas.date.domain.EventDate;
import christmas.date.exception.EventDateError;
import christmas.date.repository.EventDateRepository;
import christmas.global.exception.ChristmasPromotionException;

public class EventDateService {

    private static final EventDateService INSTANCE = new EventDateService();

    private EventDateService() {
    }

    public static EventDateService getInstance() {
        return INSTANCE;
    }

    public EventDate findEventDay(int dayOfMonth) {
        return EventDateRepository.findByDayOfMonth(dayOfMonth)
                .orElseThrow(() -> new ChristmasPromotionException(EventDateError.INVALID_DAY));
    }
}
