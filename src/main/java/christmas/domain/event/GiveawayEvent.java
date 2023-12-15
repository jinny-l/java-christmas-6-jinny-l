package christmas.domain.event;

import christmas.domain.order.Menu;
import christmas.domain.plan.Plan;
import java.util.Map;

public abstract class GiveawayEvent extends DiscountEvent {

    public GiveawayEvent(String name) {
        super(name);
    }

    public abstract Map<Menu, Integer> getGiveaways(Plan plan);
}
