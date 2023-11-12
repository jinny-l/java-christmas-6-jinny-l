package christmas.date.service;

import christmas.date.domain.EventDate;
import christmas.date.exception.EventDateError;
import christmas.date.repository.EventDateRepository;
import christmas.global.exception.ChristmasPromotionException;

public class EventDateService {

    private final EventDateRepository eventDateRepository;

    public EventDateService(EventDateRepository eventDateRepository) {
        this.eventDateRepository = eventDateRepository;
    }

    public EventDate findEventDay(int dayOfMonth) {
        return eventDateRepository.findByDayOfMonth(dayOfMonth)
                .orElseThrow(() -> new ChristmasPromotionException(EventDateError.INVALID_DAY));
    }
}
