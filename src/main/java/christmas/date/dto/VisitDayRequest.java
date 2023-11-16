package christmas.date.dto;

import christmas.date.exception.EventDateError;
import christmas.global.exception.ChristmasPromotionException;

public record VisitDayRequest(int dayOfMonth) {

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
}
