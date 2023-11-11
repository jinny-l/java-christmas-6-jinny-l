package christmas.benefit.domain;

import christmas.plan.domain.Plan;
import java.util.List;

public class Benefits {

    private final List<Benefit> benefits;

    private Benefits(List<Benefit> benefits) {
        this.benefits = benefits;
    }

    public static Benefits from(Plan plan) {
        return new Benefits(
                Event.calculateBenefits(plan)
        );
    }
}
