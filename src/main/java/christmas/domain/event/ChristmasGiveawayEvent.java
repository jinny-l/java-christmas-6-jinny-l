package christmas.domain.event;

import christmas.domain.order.Menu;
import christmas.domain.plan.Plan;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

public class ChristmasGiveawayEvent extends GiveawayEvent {

    private static final Map<Menu, Integer> GIVEAWAYS = Map.of( // TODO enumMap으로 변경
            Menu.CHAMPAGNE, 1
    );

    public ChristmasGiveawayEvent() {
        super("증정 이벤트");
    }

    @Override
    protected Function<Plan, Boolean> satisfyCondition() {
        return plan -> plan.orders().getTotalValue() > 120000;
    }

    @Override
    protected Function<Plan, Integer> discountFormula() {
        return plan -> GIVEAWAYS.entrySet().stream()
                        .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                        .sum();
    }

    @Override
    public Map<Menu, Integer> getGiveaways(Plan plan) {
        if (satisfyCondition().apply(plan)) {
            return GIVEAWAYS;
        }
        return Collections.emptyMap();
    }
}
