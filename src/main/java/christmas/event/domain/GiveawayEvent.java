package christmas.event.domain;

import christmas.order.domain.Menu;
import christmas.plan.domain.Plan;
import java.util.Map;

public abstract class GiveawayEvent extends DiscountEvent {

    public GiveawayEvent(String name) {
        super(name);
    }

    public abstract Map<Menu, Integer> getGiveaways(Plan plan);
}
