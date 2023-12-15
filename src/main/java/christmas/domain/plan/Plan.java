package christmas.domain.plan;

import christmas.domain.date.EventDate;
import christmas.domain.order.Orders;

public record Plan(
        EventDate eventDate,
        Orders orders
) {
}
