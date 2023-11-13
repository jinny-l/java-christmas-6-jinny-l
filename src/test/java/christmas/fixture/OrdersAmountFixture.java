package christmas.fixture;

import christmas.order.domain.Menu;
import christmas.order.domain.Order;
import christmas.order.domain.Orders;
import java.util.List;
import java.util.function.Function;

public enum OrdersAmountFixture {

    음료_레드와인_주문(
            amount -> List.of(new Order(Menu.RED_WINE, amount))
    ),
    메인_바비큐립_주문(
            amount -> List.of(new Order(Menu.BBQ_RIBS, amount))
    ),
    디저트_초코케이크_주문(
            amount -> List.of(new Order(Menu.CHOCO_CAKE, amount))
    );


    private final Function<Integer, List<Order>> orderCreator;

    OrdersAmountFixture(Function<Integer, List<Order>> orderCreator) {
        this.orderCreator = orderCreator;
    }

    public Orders create(int amount) {
        return new Orders(orderCreator.apply(amount));
    }
}
