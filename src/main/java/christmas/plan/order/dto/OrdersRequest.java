package christmas.plan.order.dto;

import christmas.plan.order.domain.Menu;
import christmas.plan.order.domain.Order;
import christmas.plan.order.domain.Orders;
import java.util.List;
import java.util.Map;

public record OrdersRequest(
        List<OrderRequest> menus
) {

    public static OrdersRequest from(Map<String, Integer> input) {
        return new OrdersRequest(input.entrySet().stream()
                .map(entry -> new OrderRequest(entry.getKey(), entry.getValue()))
                .toList());
    }

    public Orders toEntity() {
        return new Orders(menus.stream()
                .map(OrderRequest::toEntity)
                .toList());
    }

    private record OrderRequest(
            String name,
            int amount
    ) {

        private Order toEntity() {
            return new Order(Menu.from(name), amount);
        }
    }
}
