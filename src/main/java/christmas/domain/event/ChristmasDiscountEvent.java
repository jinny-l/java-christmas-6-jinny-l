package christmas.domain.event;

import christmas.domain.plan.Plan;
import java.util.function.Function;

public class ChristmasDiscountEvent extends DiscountEvent {

    public ChristmasDiscountEvent(String name) {
        super("크리스마스 디데이 할인");
    }

    @Override
    protected Function<Plan, Boolean> satisfyCondition() {
        return plan -> plan.eventDate().discountValue() > 0;
    }

    @Override
    protected Function<Plan, Integer> discountFormula() {
        return plan -> plan.eventDate().discountValue();
    }
}
