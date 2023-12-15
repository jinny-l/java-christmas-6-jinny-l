package christmas.dto;

public record EventDateRequest(
        int day
) {
    public static EventDateRequest from(String input) {
        int day = parseInt(input);
        validateRange(day);
        return new EventDateRequest(day);
    }

    private static int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private static void validateRange(int day) {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}
