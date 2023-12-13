package christmas.event.domain;

import christmas.plan.domain.Plan;

public interface Event {

    String getName();

    int discount(Plan plan, Integer minOrderValueCondition);
}
