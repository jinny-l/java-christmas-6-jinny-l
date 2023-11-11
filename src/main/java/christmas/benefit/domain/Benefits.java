package christmas.benefit.domain;

import christmas.plan.domain.Plan;
import java.util.List;

public record Benefits(List<Benefit> benefits) {

    public static Benefits from(Plan plan) {
        return new Benefits(
                Event.calculateBenefits(plan)
        );
    }

    public int calculateTotalDiscountAmountWithGiveawayEvent() {
        return benefits.stream()
                .mapToInt(Benefit::discountAmount)
                .sum();
    }

    public int calculateTotalDiscountAmountWithoutGiveawayEvent() {
        return benefits.stream()
                .filter(benefit -> !benefit.isGiveawayEvent())
                .mapToInt(Benefit::discountAmount)
                .sum();
    }
}
