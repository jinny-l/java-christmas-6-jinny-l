package christmas.domain.benfit;

import christmas.domain.event.Event;
import christmas.domain.plan.Plan;
import java.util.List;

public record Benefits(
        List<Benefit> benefits
) {

    public static Benefits from(Plan plan, List<Event> events) {
        return new Benefits(events.stream()
                .map(event -> Benefit.from(plan, event))
                .toList()
        );
    }

    private record Benefit(
            Event event,
            int discountValue
    ) {
        private static Benefit from(Plan plan, Event event) {
            return new Benefit(
                    event,
                    event.discount(plan)
            );
        }
    }
}
