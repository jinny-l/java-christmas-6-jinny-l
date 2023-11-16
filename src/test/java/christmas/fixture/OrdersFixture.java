package christmas.fixture;

import christmas.order.domain.Menu;
import christmas.order.domain.Order;
import christmas.order.domain.Orders;
import java.util.List;

public enum OrdersFixture {

    만원_이하_주문(
            List.of(new Order(Menu.ICE_CREAM, 1))
    ),
    음료_주문(
            List.of(new Order(Menu.RED_WINE, 1))
    ),
    음료_및_메인_주문(
            List.of(
                    new Order(Menu.RED_WINE, 1),
                    new Order(Menu.BBQ_RIBS, 1)
            )
    ),
    메인_주문_12만원_이상(
            List.of(new Order(Menu.BBQ_RIBS, 3))
    ),
    메인_주문_12만원_이하(
            List.of(new Order(Menu.BBQ_RIBS, 1))
    ),
    디저트_주문_12만원_이하(
            List.of(new Order(Menu.ICE_CREAM, 1))
    );

    private final List<Order> orders;

    OrdersFixture(List<Order> orders) {
        this.orders = orders;
    }

    public Orders create() {
        return new Orders(orders);
    }
}
