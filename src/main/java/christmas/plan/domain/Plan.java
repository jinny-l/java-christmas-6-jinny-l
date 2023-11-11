package christmas.plan.domain;

import christmas.plan.date.domain.EventDate;
import christmas.plan.order.domain.Orders;

public record Plan(
        EventDate eventDate,
        Orders orders
) {
}
