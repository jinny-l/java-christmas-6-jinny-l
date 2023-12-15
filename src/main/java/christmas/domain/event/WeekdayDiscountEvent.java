package christmas.domain.event;

import christmas.domain.date.DayType;
import christmas.domain.order.Category;
import christmas.domain.plan.Plan;
import java.util.function.Function;

public class WeekdayDiscountEvent extends DiscountEvent {

    public WeekdayDiscountEvent() {
        super("평일 할인");
    }

    @Override
    protected Function<Plan, Boolean> satisfyCondition() {
        return plan -> plan.eventDate().dayType() == DayType.WEEKDAY;
    }

    @Override
    protected Function<Plan, Integer> discountFormula() {
        return plan -> plan.orders().getMenuAmountBy(Category.DESSERT) * 2023;
    }
}
