package christmas.benefit.dto;

import christmas.benefit.domain.Benefit;
import christmas.benefit.domain.Benefits;
import christmas.order.domain.Menu;
import java.util.List;
import java.util.Map;

public record BenefitsResponse(
        List<BenefitResponse> benefits,
        Map<Menu, Integer> giveaways
) {

    public static BenefitsResponse from(Benefits benefits, Map<Menu, Integer> giveaways) {
        return new BenefitsResponse(
                benefits.benefits().stream()
                        .filter(Benefit::hasDiscount)
                        .map(BenefitResponse::from)
                        .toList(),
                giveaways
        );
    }

    public record BenefitResponse(
            String eventName,
            int discountAmount
    ) {

        private static BenefitResponse from(Benefit benefit) {
            return new BenefitResponse(
                    benefit.event().getName(),
                    benefit.discountValue()
            );
        }
    }
}
