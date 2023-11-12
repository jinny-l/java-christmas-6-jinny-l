package christmas.date.service;

import christmas.date.domain.EventDate;
import christmas.date.dto.VisitDayRequest;
import christmas.date.repository.EventDateRepository;

public class EventDateService {

    private final EventDateRepository eventDateRepository;

    public EventDateService(EventDateRepository eventDateRepository) {
        this.eventDateRepository = eventDateRepository;
    }

    public EventDate findEventDay(VisitDayRequest request) {
        return eventDateRepository.findByDayOfMonth(request.dayOfMonth())
                .orElseThrow(IllegalArgumentException::new);
    }
}
