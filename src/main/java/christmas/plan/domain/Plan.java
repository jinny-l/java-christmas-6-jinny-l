package christmas.plan.domain;

import christmas.date.domain.EventDate;
import christmas.order.domain.Orders;

public record Plan(
        EventDate eventDate,
        Orders orders
) {
}
