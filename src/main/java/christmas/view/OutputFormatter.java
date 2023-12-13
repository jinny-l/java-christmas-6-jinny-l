package christmas.view;

import christmas.benefit.dto.BenefitsResponse;
import christmas.order.dto.OrdersResponse;
import christmas.payment.dto.PaymentResponse;
import java.util.stream.Collectors;

public class OutputFormatter {

    private OutputFormatter() {
    }

    public static String formatOrdersMenu(OrdersResponse ordersResponse) {
        return ordersResponse.orders().stream()
                .map(orderResponse ->
                        String.format("%s %d개", orderResponse.name(), orderResponse.amount()))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    public static String formatBenefits(BenefitsResponse benefitsResponse) {
        if (benefitsResponse.benefits().isEmpty()) {
            return "없음";
        }
        return benefitsResponse.benefits().stream()
                .map(benefitResponse ->
                        String.format("%s: -%,d원", benefitResponse.eventName(), benefitResponse.discountAmount()))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    public static String formatDiscountValue(PaymentResponse paymentResponse) {
        if (paymentResponse.discountValue() == 0) {
            return "0원";
        }
        return String.format("-%,d원", paymentResponse.discountValue());
    }
}
