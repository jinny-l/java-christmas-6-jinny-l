package christmas.order.service;

import christmas.global.exception.ChristmasPromotionException;
import christmas.order.domain.Orders;
import christmas.order.dto.OrdersRequest;
import christmas.order.exception.OrderError;

// TODO: 도메인 내부로 로직을 이동시킬지 고민중
public class OrderService {

    private static final OrderService ORDER_SERVICE = new OrderService();
    private static final int ORDER_AMOUNT_MAX_VALUE = 20;

    private OrderService() {
    }

    public static OrderService getInstance() {
        return ORDER_SERVICE;
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
