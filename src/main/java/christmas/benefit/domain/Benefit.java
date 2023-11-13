package christmas.benefit.domain;

import christmas.plan.domain.Plan;

public record Benefit(
        Event event,
        int discountValue
) {

    public static Benefit from(Event event, Plan plan) {
        return new Benefit(
                event,
                event.calculateDiscountValue(plan)
        );
    }

    public boolean isGiveawayEvent() {
        return event == Event.GIVEAWAY;
    }

    public boolean isZeroDiscount() {
        return discountValue == 0;
    }
}
