package christmas.fixture;

import christmas.plan.order.domain.Menu;
import christmas.plan.order.domain.Order;
import christmas.plan.order.domain.Orders;
import java.util.List;

public enum OrdersFixture {

    ONLY_MAIN_SATISFIED_GIVEAWAY_EVENT(List.of(new Order(Menu.BBQ_RIBS, 3))),
    ONLY_MAIN_NOT_SATISFIED_GIVEAWAY_EVENT(List.of(new Order(Menu.BBQ_RIBS, 1)));

    private final List<Order> orders;

    OrdersFixture(List<Order> orders) {
        this.orders = orders;
    }

    public Orders create() {
        return new Orders(orders);
    }
}
