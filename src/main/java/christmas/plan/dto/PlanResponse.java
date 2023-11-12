package christmas.plan.dto;

import christmas.date.dto.EventDateResponse;
import christmas.plan.domain.Plan;
import christmas.plan.order.dto.OrdersResponse;

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
