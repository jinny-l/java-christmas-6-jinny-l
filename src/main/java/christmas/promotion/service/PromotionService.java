package christmas.promotion.service;

import christmas.benefit.domain.Benefits;
import christmas.plan.domain.Plan;
import christmas.promotion.domain.Badge;
import christmas.promotion.domain.Promotion;

public class PromotionService {

    public PromotionService() {
    }

    public Promotion createPromotion(Plan plan, Benefits benefits) {
        Badge badge = Badge.from(
                benefits.calculateTotalDiscountAmountWithGiveawayEvent()
        );
        return new Promotion(plan, benefits, badge);
    }
}
