package christmas.event.domain;

import christmas.plan.domain.Plan;
import java.util.function.Function;

public class ChristmasEvent extends DiscountEvent {

    public ChristmasEvent() {
        super("크리스마스 디데이 할인");
    }

    @Override
    protected boolean satisfyCondition(Plan plan) {
        return plan.eventDate().discountAmount() > 0;
    }

    @Override
    protected Function<Plan, Integer> discountFormula() {
        return plan -> plan.eventDate().discountAmount();
    }
}
