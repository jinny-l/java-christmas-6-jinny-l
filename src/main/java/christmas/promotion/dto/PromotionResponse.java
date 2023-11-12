package christmas.promotion.dto;

import christmas.benefit.dto.BenefitsResponse;
import christmas.payment.dto.PaymentResponse;
import christmas.plan.dto.PlanResponse;
import christmas.promotion.domain.Promotion;

public record PromotionResponse(
        PlanResponse planResponse,
        BenefitsResponse benefitsResponse,
        BadgeResponse badgeResponse,
        PaymentResponse paymentResponse
) {

    public static PromotionResponse from(Promotion promotion) {
        return new PromotionResponse(
                PlanResponse.from(promotion.plan()),
                BenefitsResponse.from(promotion.benefits()),
                BadgeResponse.from(promotion.badge()),
                PaymentResponse.from(promotion.payment())
        );
    }
}
