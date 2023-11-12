package christmas.benefit.dto;

import christmas.benefit.domain.Benefit;
import christmas.benefit.domain.Benefits;
import java.util.List;

public record BenefitsResponse(
        List<BenefitResponse> benefits,
        boolean haveGiveaway
) {

    public static BenefitsResponse from(Benefits benefits) {
        return new BenefitsResponse(benefits.benefits().stream()
                .filter(benefit -> !benefit.isZeroDiscount())
                .map(BenefitResponse::from)
                .toList(),
                benefits.haveGiveaway()
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
