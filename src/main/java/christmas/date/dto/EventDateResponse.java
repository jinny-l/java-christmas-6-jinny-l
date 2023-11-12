package christmas.date.dto;

import christmas.date.domain.EventDate;

public record EventDateResponse(
        int dayOfMonth
) {
    public static EventDateResponse from(EventDate eventDate) {
        return new EventDateResponse(eventDate.date().getDayOfMonth());
    }
}
