package christmas.domain.event;

import christmas.domain.plan.Plan;

public interface Event {

    String getName();

    int discount(Plan plan);
}
