package christmas.date.dto;

import christmas.date.domain.EventDate;

public record VisitDayResponse(
        int dayOfMonth
) {
    public static VisitDayResponse from(EventDate eventDate) {
        return new VisitDayResponse(eventDate.date().getDayOfMonth());
    }
}
