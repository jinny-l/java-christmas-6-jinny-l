package christmas.event.domain;

import christmas.order.domain.Menu;
import christmas.plan.domain.Plan;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public class DecemberGiveawayEvent extends GiveawayEvent {

    private static final int MIN_ORDER_VALUE_FOR_GIVEAWAY = 120000;
    private static final Map<Menu, Integer> GIVEAWAY = new EnumMap<Menu, Integer>(Menu.class);

    static {
        GIVEAWAY.putAll(
                Map.of(Menu.CHAMPAGNE, 1)
        );
    }

    public DecemberGiveawayEvent() {
        super("증정 이벤트");
    }

    @Override
    protected boolean satisfyCondition(Plan plan) {
        return plan.orders().calculateTotalValue() >= MIN_ORDER_VALUE_FOR_GIVEAWAY;
    }

    @Override
    protected Function<Plan, Integer> discountFormula() {
        return plan ->  Menu.CHAMPAGNE.getPrice();
    }

    @Override
    public Map<Menu, Integer> getGiveaway() {
        return Collections.unmodifiableMap(GIVEAWAY);
    }
}
