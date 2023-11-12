package christmas.plan.date.dto;

import christmas.plan.date.domain.EventDate;

public record EventDateResponse(
        int dayOfMonth
) {
    public static EventDateResponse from(EventDate eventDate) {
        return new EventDateResponse(eventDate.date().getDayOfMonth());
    }
}
