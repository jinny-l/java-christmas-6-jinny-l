package christmas.payment.domain;

public record Payment(
        int totalValue,
        int discountValue,
        int finalValue
) {
}
