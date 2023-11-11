package christmas.benefit.domain;

import christmas.plan.domain.Plan;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public enum Event {

    D_DAY("크리스마스 디데이 할인",
            planner -> planner.eventDate().discountAmount()
    ),
    WEEKDAY("평일 할인",
            planner -> {
                if (planner.eventDate().isWeekday()) {
                    return planner.orders().countAmountByDessertMenu() * 2023;
                }
                return 0;
            }
    ),
    WEEKEND("주말 할인",
            planner -> {
                if (planner.eventDate().isWeekend()) {
                    return planner.orders().countAmountByMainMenu() * 2023;
                }
                return 0;
            }
    ),
    STAR_DAY("특별 할인",
            planner -> {
                if (planner.eventDate().hasStar()) {
                    return 1000;
                }
                return 0;
            }
    ),
    GIVEAWAY("증정 이벤트",
            planner -> {
                if (planner.orders().getTotalValue() > 120000) {
                    return 25000;
                }
                return 0;
            }
    );

    private final String name;
    private final Function<Plan, Integer> discountAmountFormula;

    Event(String name, Function<Plan, Integer> discountAmountFormula) {
        this.name = name;
        this.discountAmountFormula = discountAmountFormula;
    }

    public String getName() {
        return name;
    }

    public static List<Benefit> calculateBenefits(Plan plan) {
        return Arrays.stream(Event.values())
                .map(event -> new Benefit(
                        event,
                        event.calculateDiscountAmount(plan))
                )
                .toList();
    }

    public int calculateDiscountAmount(Plan plan) {
        if (plan.orders().getTotalValue() < 10000) {
            return 0;
        }
        return this.discountAmountFormula.apply(plan);
    }
}
