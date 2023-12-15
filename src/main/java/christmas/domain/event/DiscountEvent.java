package christmas.domain.event;

import christmas.domain.plan.Plan;
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
    public int discount(Plan plan) {
        if (plan.orders().getTotalValue() < 10000) {
            return 0;
        }

        if (!satisfyCondition().apply(plan)) {
            return 0;
        }
        return discountFormula().apply(plan);
    }

    protected abstract Function<Plan, Boolean> satisfyCondition();

    protected abstract Function<Plan, Integer> discountFormula();
}
