package christmas.benefit.config;

import christmas.order.domain.Menu;

public final class EventConfig {

    public static final int WEEKDAY_DISCOUNT_VALUE = 2023;
    public static final int WEEKEND_DISCOUNT_VALUE = 2023;
    public static final int STAR_DAY_DISCOUNT_VALUE = 1000;
    public static final int MIN_ORDER_VALUE_FOR_GIVEAWAY = 120000;
    public static final int GIVEAWAY_DISCOUNT_VALUE = Menu.CHAMPAGNE.getPrice();
    public static final int MIN_ORDER_VALUE_FOR_EVENT = 10000;

    private EventConfig() {
    }


}
