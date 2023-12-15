package christmas.domain.event;

import christmas.domain.plan.Plan;
import java.util.function.Function;

public class StarDayDiscountEvent extends DiscountEvent {

    public StarDayDiscountEvent(String name) {
        super("특별 할인");
    }

    @Override
    protected Function<Plan, Boolean> satisfyCondition() {
        return plan -> plan.eventDate().hasStar();
    }

    @Override
    protected Function<Plan, Integer> discountFormula() {
        return plan -> 1000;
    }
}
