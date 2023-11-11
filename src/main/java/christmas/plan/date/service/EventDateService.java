package christmas.plan.date.service;

import christmas.plan.date.domain.EventDate;
import christmas.plan.date.dto.VisitDayRequest;
import christmas.plan.date.repository.EventDateRepository;

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
