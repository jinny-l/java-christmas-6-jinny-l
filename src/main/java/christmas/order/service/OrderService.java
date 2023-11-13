package christmas.order.service;

import christmas.global.exception.ChristmasPromotionException;
import christmas.order.domain.Orders;
import christmas.order.dto.OrdersRequest;
import christmas.order.exception.OrderError;

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
            throw new ChristmasPromotionException(OrderError.ALL_DRINK_ORDER);
        }
    }

    private void validateOrderAmount(Orders orders) {
        if (orders.calculateTotalAmount() > ORDER_AMOUNT_MAX_VALUE) {
            throw new ChristmasPromotionException(OrderError.EXCEEDED_MAX_ORDER_AMOUNT);
        }
    }
}
