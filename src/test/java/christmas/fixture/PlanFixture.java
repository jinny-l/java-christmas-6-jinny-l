package christmas.fixture;

import christmas.date.domain.EventDate;
import christmas.order.domain.Orders;
import christmas.plan.domain.Plan;

public enum PlanFixture {

    디데이O_별X_평일_메인_주문_12만원_이상(
            EventDateFixture.디데이O_별X_평일_4일.create(),
            OrdersFixture.메인_주문_12만원_이상.create()
    ),
    디데이O_별X_평일_메인_주문_12만원_이하(
            EventDateFixture.디데이O_별X_평일_4일.create(),
            OrdersFixture.메인_주문_12만원_이하.create()
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
