package christmas.event.domain;

import christmas.plan.domain.Plan;
import java.util.function.Function;

public class WeekdayEvent extends DiscountEvent {

    private static final int WEEKDAY_DISCOUNT_VALUE = 2023;

    public WeekdayEvent(String name) {
        super(name);
    }

    @Override
    protected boolean satisfyCondition(Plan plan) {
        return plan.eventDate().isWeekday();
    }

    @Override
    protected Function<Plan, Integer> discountFormula() {
        return plan -> plan.orders().countAmountByDessertMenu() * WEEKDAY_DISCOUNT_VALUE;
    }
}
