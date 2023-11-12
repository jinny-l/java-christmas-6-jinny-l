package christmas.fixture;

import christmas.order.domain.Menu;
import christmas.order.domain.Order;
import christmas.order.domain.Orders;
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
