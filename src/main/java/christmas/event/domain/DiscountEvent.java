package christmas.event.domain;

import christmas.plan.domain.Plan;
import java.util.function.Function;

public abstract class DiscountEvent implements Event {

    private final String name;

    public DiscountEvent(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int discount(Plan plan, Integer minOrderValueCondition) {
        if (minOrderValueCondition != null
                && plan.orders().calculateTotalValue() <= minOrderValueCondition
        ) {
            return 0;
        }

        if (!satisfyCondition(plan)) {
            return 0;
        }

        return discountFormula().apply(plan);
    }

    protected abstract boolean satisfyCondition(Plan plan);

    protected abstract Function<Plan, Integer> discountFormula();
}
