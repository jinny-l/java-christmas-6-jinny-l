package christmas.event.domain;

import christmas.plan.domain.Plan;
import java.util.function.Function;

public class WeekendEvent extends DiscountEvent {

    private static final int WEEKEND_DISCOUNT_VALUE = 2023;

    public WeekendEvent(String name) {
        super(name);
    }

    @Override
    protected boolean satisfyCondition(Plan plan) {
        return plan.eventDate().isWeekend();
    }

    @Override
    protected Function<Plan, Integer> discountFormula() {
        return plan -> plan.orders().countAmountByMainMenu() * WEEKEND_DISCOUNT_VALUE;
    }
}
