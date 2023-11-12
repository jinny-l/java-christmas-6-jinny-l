package christmas.order.service;

import christmas.order.domain.Orders;
import christmas.order.dto.OrdersRequest;

public class OrderService {

    private static final int ORDER_AMOUNT_MAX_VALUE = 20;

    public OrderService() {
    }

    public Orders createOrders(OrdersRequest ordersRequest) {
        Orders orders = ordersRequest.toEntity();

        validateOrderAmount(orders);
        validateOrderFood(orders);

        return orders;
    }

    private void validateOrderFood(Orders orders) {
        if (orders.isAllDrink()) {
            throw new IllegalArgumentException();
        }
    }

    private void validateOrderAmount(Orders orders) {
        if (orders.totalAmount() > ORDER_AMOUNT_MAX_VALUE) {
            throw new IllegalArgumentException();
        }
    }
}
