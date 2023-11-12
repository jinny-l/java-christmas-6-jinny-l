package christmas.order.exception;

import christmas.global.exception.Error;

public enum OrderError implements Error {

    INVALID_FORMAT("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_AMOUNT("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    DUPLICATE_MENU("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_MENU("메뉴에 없는 주문입니다."),
    ALL_DRINK_ORDER("음료만 주문 시, 주문할 수 없습니다."),
    EXCEEDED_MAX_ORDER_AMOUNT("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.");

    private final String message;

    OrderError(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
