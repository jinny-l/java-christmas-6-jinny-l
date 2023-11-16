package christmas.promotion.domain;

import christmas.benefit.domain.Benefits;
import christmas.payment.domain.Payment;
import christmas.plan.domain.Plan;

public record Promotion(
        Plan plan,
        Benefits benefits,
        Badge badge,
        Payment payment
) {
}
