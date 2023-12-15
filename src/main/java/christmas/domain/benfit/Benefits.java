package christmas.domain.benfit;

import christmas.domain.event.Event;
import christmas.domain.event.GiveawayEvent;
import christmas.domain.order.Menu;
import christmas.domain.plan.Plan;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

// GiveawayBenefit DiscountBenefit이랑 나눠야 하나
public record Benefits(
        List<Benefit> benefits
) {

    public static Benefits from(Plan plan, List<Event> events) {
        return new Benefits(events.stream()
                .map(event -> Benefit.from(plan, event))
                .toList()
        );
    }

    public Map<Menu, Integer> getGiveaways(Plan plan) { // 증정 이벤트 별로 출력해야 하면 지금 형태의 Map을 별도 클래스로 한번 더 묶고, 맵으로 랩핑
        return benefits.stream()
                .filter(benefit -> benefit.event instanceof GiveawayEvent)
                .map(benefit -> (GiveawayEvent) benefit.event)
                .flatMap(giveawayEvent -> giveawayEvent.getGiveaways(plan).entrySet().stream())
                .collect(Collectors.toMap(
                        Entry::getKey,
                        Entry::getValue
                ));
    }

    public int getTotalDiscountValue() {
        return benefits.stream()
                .mapToInt(benefit -> benefit.discountValue)
                .sum();
    }

    public int getGiveawayDiscountValue() {
        return benefits.stream()
                .filter(benefit -> benefit.event instanceof GiveawayEvent)
                .mapToInt(benefit -> benefit.discountValue)
                .sum();
    }

    public record Benefit(
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
