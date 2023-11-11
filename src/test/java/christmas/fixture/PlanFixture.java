package christmas.fixture;

import christmas.plan.date.domain.EventDate;
import christmas.plan.domain.Plan;
import christmas.plan.order.domain.Orders;

public enum PlanFixture {

    WEEKDAY_NO_STAR_WITH_DDAY_AND_GIVEAWAY(
            EventDateFixture.WEEKDAY_NO_STAR.create(),
            OrdersFixture.ONLY_MAIN_SATISFIED_GIVEAWAY_EVENT.create()
    ),
    WEEKDAY_NO_STAR_WITH_DDAY_AND_NO_GIVEAWAY(
            EventDateFixture.WEEKDAY_NO_STAR.create(),
            OrdersFixture.ONLY_MAIN_NOT_SATISFIED_GIVEAWAY_EVENT.create()
    );

    private final EventDate eventDate;
    private final Orders orders;

    PlanFixture(EventDate eventDate, Orders orders) {
        this.eventDate = eventDate;
        this.orders = orders;
    }

    public Plan create() {
        return new Plan(eventDate, orders);
    }
}
