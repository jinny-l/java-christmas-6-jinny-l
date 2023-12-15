package christmas.domain.event;

import christmas.domain.date.DayType;
import christmas.domain.order.Category;
import christmas.domain.plan.Plan;
import java.util.function.Function;

public class WeekendDiscountEvent extends DiscountEvent {

    public WeekendDiscountEvent() {
        super("주말 할인");
    }

    @Override
    protected Function<Plan, Boolean> satisfyCondition() {
        return plan -> plan.eventDate().dayType() == DayType.WEEKEND;
    }

    @Override
    protected Function<Plan, Integer> discountFormula() {
        return plan -> plan.orders().getMenuAmountBy(Category.MAIN_COURSE) * 2023;
    }
}
