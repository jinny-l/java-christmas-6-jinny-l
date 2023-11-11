package christmas.payment.service;

import christmas.benefit.domain.Benefits;
import christmas.payment.domain.Payment;
import christmas.plan.order.domain.Orders;

public class PaymentService {

    public PaymentService() {
    }

    public Payment createPayment(Orders orders, Benefits benefits) {
        int totalValue = orders.getTotalValue();

        return new Payment(
                totalValue,
                benefits.calculateDiscountValue(),
                calculateFinalValue(totalValue, benefits)
        );
    }

    private int calculateFinalValue(int totalValue, Benefits benefits) {
        if (benefits.haveGiveaway()) {
            return totalValue - benefits.calculateDiscountValue() + benefits.calculateGiveawayDiscountValue();
        }
        return totalValue - benefits.calculateDiscountValue();
    }
}
