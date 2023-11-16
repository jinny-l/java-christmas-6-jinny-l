package christmas.date.exception;

import christmas.global.exception.Error;

public enum EventDateError implements Error {

    INTERNAL_ERROR("내부 오류"),
    INVALID_DAY("유효하지 않은 날짜입니다. 다시 입력해 주세요.");

    private final String message;

    EventDateError(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
