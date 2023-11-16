package christmas.payment.dto;

import christmas.payment.domain.Payment;

public record PaymentResponse(
        int totalValue,
        int discountValue,
        int finalValue
) {

    public static PaymentResponse from(Payment payment) {
        return new PaymentResponse(
                payment.totalValue(),
                payment.discountValue(),
                payment.finalValue()
        );
    }
}
