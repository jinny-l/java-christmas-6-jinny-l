package christmas.event.service;

import christmas.event.domain.EventDay;
import christmas.event.dto.VisitDayRequest;
import christmas.event.repository.EventDayRepository;

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
