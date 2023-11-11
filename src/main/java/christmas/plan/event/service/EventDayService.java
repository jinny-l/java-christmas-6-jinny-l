package christmas.plan.event.service;

import christmas.plan.event.domain.EventDay;
import christmas.plan.event.dto.VisitDayRequest;
import christmas.plan.event.repository.EventDayRepository;

public class EventDayService {

    private final EventDayRepository eventDayRepository;

    public EventDayService(EventDayRepository eventDayRepository) {
        this.eventDayRepository = eventDayRepository;
    }

    public EventDay findEventDay(VisitDayRequest request) {
        return eventDayRepository.findByDayOfMonth(request.dayOfMonth())
                .orElseThrow(IllegalArgumentException::new);
    }
}
