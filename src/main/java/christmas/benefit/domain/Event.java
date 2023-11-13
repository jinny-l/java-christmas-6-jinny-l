package christmas.benefit.domain;

import christmas.benefit.config.EventConfig;
import christmas.plan.domain.Plan;
import java.util.function.Function;

public enum Event {

    D_DAY("크리스마스 디데이 할인",
            plan -> true, // 크리스마스 디데이 할인 조건은 EventDate 가 처리하고 있어서 체크할 필요 없음
            plan -> plan.eventDate().discountAmount()
    ),
    WEEKDAY("평일 할인",
            plan -> plan.eventDate().isWeekday(),
            plan -> plan.orders().countAmountByDessertMenu() * EventConfig.WEEKDAY_DISCOUNT_VALUE
    ),
    WEEKEND("주말 할인",
            plan -> plan.eventDate().isWeekend(),
            plan -> plan.orders().countAmountByMainMenu() * EventConfig.WEEKEND_DISCOUNT_VALUE
    ),
    STAR_DAY("특별 할인",
            plan -> plan.eventDate().hasStar(),
            plan -> EventConfig.STAR_DAY_DISCOUNT_VALUE
    ),
    GIVEAWAY("증정 이벤트",
            plan -> plan.orders().calculateTotalValue() > EventConfig.MIN_ORDER_VALUE_FOR_GIVEAWAY,
            plan -> EventConfig.GIVEAWAY_DISCOUNT_VALUE
    );

    private final String name;
    private final Function<Plan, Boolean> conditionFormula;
    private final Function<Plan, Integer> discountValueFormula;

    Event(
            String name,
            Function<Plan, Boolean> conditionFormula,
            Function<Plan, Integer> discountValueFormula
    ) {
        this.name = name;
        this.conditionFormula = conditionFormula;
        this.discountValueFormula = discountValueFormula;
    }

    public String getName() {
        return name;
    }

    public int calculateDiscountValue(Plan plan) {
        if (plan.orders().calculateTotalValue() < EventConfig.MIN_ORDER_VALUE_FOR_EVENT) {
            return 0;
        }
        if (!conditionFormula.apply(plan)) {
            return 0;
        }
        return this.discountValueFormula.apply(plan);
    }
}
