package christmas.promotion.service;

import christmas.benefit.domain.Benefits;
import christmas.payment.domain.Payment;
import christmas.plan.domain.Plan;
import christmas.promotion.domain.Badge;
import christmas.promotion.domain.Promotion;

public class PromotionService {

    public PromotionService() {
    }

    public Promotion createPromotion(Plan plan, Benefits benefits, Payment payment) {
        Badge badge = Badge.from(
                benefits.calculateDiscountValue()
        );
        return new Promotion(plan, benefits, badge, payment);
    }
}
