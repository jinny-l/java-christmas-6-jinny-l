package christmas.payment.domain;

import christmas.benefit.domain.Benefits;
import christmas.order.domain.Orders;

public record Payment(
        int totalValue,
        int discountValue,
        int finalValue
) {

    public static Payment from(Orders orders, Benefits benefits) {
        int totalValue = orders.calculateTotalValue();

        return new Payment(
                totalValue,
                benefits.calculateTotalDiscountValue(),
                calculateFinalValue(orders, benefits)
        );
    }

    private static int calculateFinalValue(Orders orders, Benefits benefits) {
        int valueAfterDiscount = orders.calculateTotalValue()
                - benefits.calculateTotalDiscountValue();

        if (benefits.haveGiveaway()) {
            return valueAfterDiscount
                    + benefits.calculateGiveawayDiscountValue();
        }

        return valueAfterDiscount;
    }
}
