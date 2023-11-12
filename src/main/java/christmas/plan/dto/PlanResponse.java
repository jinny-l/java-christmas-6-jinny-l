package christmas.plan.dto;

import christmas.date.dto.EventDateResponse;
import christmas.order.dto.OrdersResponse;
import christmas.plan.domain.Plan;

public record PlanResponse(
        EventDateResponse eventDateResponse,
        OrdersResponse ordersResponse
) {

    public static PlanResponse from(Plan plan) {
        return new PlanResponse(
                EventDateResponse.from(plan.eventDate()),
                OrdersResponse.from(plan.orders())
        );
    }
}
