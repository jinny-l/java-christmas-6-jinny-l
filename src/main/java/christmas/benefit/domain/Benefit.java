package christmas.benefit.domain;

import christmas.event.domain.Event;
import christmas.event.domain.GiveawayEvent;
import christmas.plan.domain.Plan;

public record Benefit(
        Event event,
        int discountValue
) {

    private static final int MIN_ORDER_VALUE_FOR_BENEFIT = 10000;

    public static Benefit from(Event event, Plan plan) {
        return new Benefit(
                event,
                event.discount(plan, MIN_ORDER_VALUE_FOR_BENEFIT)
        );
    }

    public boolean isGiveawayEvent() {
        return event instanceof GiveawayEvent;
    }

    public boolean hasDiscount() {
        return discountValue != 0;
    }
}
