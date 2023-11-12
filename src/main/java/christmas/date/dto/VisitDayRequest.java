package christmas.date.dto;

import christmas.date.exception.EventDateError;
import christmas.global.exception.ChristmasPromotionException;

public record VisitDayRequest(int dayOfMonth) {

    private static final int EVENT_START_DAY = 1;
    private static final int EVENT_END_DAY = 31;

    public VisitDayRequest {
        validateDayOfMonth(dayOfMonth);
    }

    public static VisitDayRequest from(String input) {
        int dayOfMonth = validateAndParseInt(input);
        return new VisitDayRequest(dayOfMonth);
    }

    private static int validateAndParseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new ChristmasPromotionException(EventDateError.INVALID_DAY);
        }
    }

    private void validateDayOfMonth(int dayOfMonth) {
        if (dayOfMonth < EVENT_START_DAY || dayOfMonth > EVENT_END_DAY) {
            throw new ChristmasPromotionException(EventDateError.INVALID_DAY);
        }
    }
}
