package christmas.view.exception;

import christmas.global.exception.Error;

public enum InputError implements Error {

    BLANK_INPUT("입력 값이 없거나 구분자 사이에 값이 없습니다. 다시 입력해 주세요."),
    INVALID_ORDERS_FORMAT("유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private final String message;

    InputError(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
