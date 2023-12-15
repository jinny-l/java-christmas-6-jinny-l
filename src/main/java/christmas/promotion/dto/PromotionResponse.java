package christmas.promotion.dto;

import christmas.benefit.dto.BenefitsResponse;
import christmas.order.domain.Menu;
import christmas.payment.dto.PaymentResponse;
import christmas.plan.dto.PlanResponse;
import christmas.promotion.domain.Promotion;
import java.util.Map;

public record PromotionResponse(
        PlanResponse planResponse,
        BenefitsResponse benefitsResponse,
        BadgeResponse badgeResponse,
        PaymentResponse paymentResponse
) {

    public static PromotionResponse from(Promotion promotion, Map<Menu, Integer> giveaways) {
        return new PromotionResponse(
                PlanResponse.from(promotion.plan()),
                BenefitsResponse.from(promotion.benefits(), giveaways),
                BadgeResponse.from(promotion.badge()),
                PaymentResponse.from(promotion.payment())
        );
    }
}
