package christmas.benefit.domain;

import christmas.plan.domain.Plan;
import java.util.Arrays;
import java.util.List;

public record Benefits(
        List<Benefit> benefits
) {

    public static Benefits from(Plan plan) {
        return new Benefits(
                Arrays.stream(Event.values())
                        .map(event -> Benefit.from(event, plan))
                        .toList()
        );
    }

    public int calculateDiscountValue() {
        return benefits.stream()
                .mapToInt(Benefit::discountValue)
                .sum();
    }

    public boolean haveGiveaway() {
        return benefits.stream()
                .anyMatch(benefit ->
                        benefit.isGiveawayEvent()
                                && benefit.discountValue() != 0
                );
    }

    public int calculateGiveawayDiscountValue() {
        return benefits.stream()
                .filter(Benefit::isGiveawayEvent)
                .findAny()
                .map(Benefit::discountValue)
                .orElse(0);
    }
}
