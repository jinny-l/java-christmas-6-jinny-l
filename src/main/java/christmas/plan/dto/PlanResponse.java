package christmas.plan.dto;

import christmas.date.dto.VisitDayResponse;
import christmas.order.dto.OrdersResponse;
import christmas.plan.domain.Plan;

public record PlanResponse(
        VisitDayResponse visitDayResponse,
        OrdersResponse ordersResponse
) {

    public static PlanResponse from(Plan plan) {
        return new PlanResponse(
                VisitDayResponse.from(plan.eventDate()),
                OrdersResponse.from(plan.orders())
        );
    }
}
