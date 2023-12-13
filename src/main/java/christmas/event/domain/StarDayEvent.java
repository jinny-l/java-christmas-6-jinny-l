package christmas.event.domain;

import christmas.plan.domain.Plan;
import java.util.function.Function;

public class StarDayEvent extends DiscountEvent {

    private static final int STAR_DAY_DISCOUNT_VALUE = 1000;

    public StarDayEvent(String name) {
        super(name);
    }

    @Override
    protected boolean satisfyCondition(Plan plan) {
        return plan.eventDate().hasStar();
    }

    @Override
    protected Function<Plan, Integer> discountFormula() {
        return plan -> STAR_DAY_DISCOUNT_VALUE;
    }
}
