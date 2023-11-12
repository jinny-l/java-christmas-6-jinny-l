package christmas.plan.order.dto;

import christmas.plan.order.domain.Order;
import christmas.plan.order.domain.Orders;
import java.util.List;

public record OrdersResponse(
        List<OrderResponse> menus
) {

    public static OrdersResponse from(Orders orders) {
        return new OrdersResponse(orders.menus().stream()
                .map(OrderResponse::from)
                .toList()
        );
    }

    public record OrderResponse(
            String name,
            int amount
    ) {

        private static OrderResponse from(Order order) {
            return new OrderResponse(
                    order.menu().getName(),
                    order.amount()
            );
        }
    }
}
