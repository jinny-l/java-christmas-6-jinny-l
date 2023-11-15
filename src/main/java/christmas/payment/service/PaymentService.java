package christmas.payment.service;

import christmas.benefit.domain.Benefits;
import christmas.order.domain.Orders;
import christmas.payment.domain.Payment;

public class PaymentService {

    public static final PaymentService PAYMENT_SERVICE = new PaymentService();

    private PaymentService() {
    }

    public static PaymentService getInstance() {
        return PAYMENT_SERVICE;
    }

    public Payment createPayment(Orders orders, Benefits benefits) {
        int totalValue = orders.calculateTotalValue();

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
