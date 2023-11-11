package christmas.promotion.domain;

import christmas.benefit.domain.Benefits;
import christmas.plan.domain.Plan;

public record Promotion(
        Plan plan,
        Benefits benefits,
        Badge badge
) {
}
