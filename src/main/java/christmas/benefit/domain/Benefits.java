package christmas.benefit.domain;

import christmas.event.domain.Event;
import christmas.plan.domain.Plan;
import java.util.List;

public record Benefits(
        List<Benefit> benefits
) {

    public static Benefits from(Plan plan, List<Event> events) {
        return new Benefits(
                events.stream()
                        .map(event -> Benefit.from(event, plan))
                        .toList()
        );
    }

    public int calculateTotalDiscountValue() {
        return benefits.stream()
                .mapToInt(Benefit::discountValue)
                .sum();
    }

    public boolean haveGiveaway() {
        return benefits.stream()
                .anyMatch(benefit ->
                        benefit.isGiveawayEvent()
                                && benefit.hasDiscount()
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
