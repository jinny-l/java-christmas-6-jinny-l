package christmas.event.domain;

import christmas.order.domain.Menu;
import java.util.Map;

public abstract class GiveawayEvent extends DiscountEvent {

    public GiveawayEvent(String name) {
        super(name);
    }

    public abstract Map<Menu, Integer> getGiveaway();
}
