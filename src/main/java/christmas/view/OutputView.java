package christmas.view;

import christmas.benefit.dto.BenefitsResponse;
import christmas.order.dto.OrdersResponse;
import christmas.payment.dto.PaymentResponse;
import christmas.promotion.dto.BadgeResponse;
import christmas.promotion.dto.PromotionResponse;
import java.util.stream.Collectors;

public class OutputView {

    private OutputView() {
    }

    public static void printError(Exception e) {
        System.out.printf("[ERROR] %s%n", e.getMessage());
    }

    public static void printGreetingMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public static void printPromotion(PromotionResponse promotionResponse) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n%n",
                promotionResponse.planResponse().visitDayResponse().dayOfMonth()
        );

        printOrders(promotionResponse.planResponse().ordersResponse());
        printTotalValue(promotionResponse.paymentResponse());

        printGiveaway(promotionResponse.benefitsResponse());
        printBenefits(promotionResponse.benefitsResponse());

        printDiscountValue(promotionResponse.paymentResponse());
        printFinalValue(promotionResponse.paymentResponse());

        printBadge(promotionResponse.badgeResponse());
    }

    private static void printOrders(OrdersResponse ordersResponse) {
        System.out.printf(
                "<주문 메뉴>%n%s%n%n",
                formatOrdersMenu(ordersResponse)
        );
    }

    private static String formatOrdersMenu(OrdersResponse ordersResponse) {
        return ordersResponse.menus().stream()
                .map(orderResponse ->
                        String.format("%s %d개", orderResponse.name(), orderResponse.amount()))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private static void printTotalValue(PaymentResponse paymentResponse) {
        System.out.printf(
                "<할인 전 총주문 금액>%n%,d원%n%n",
                paymentResponse.totalValue()
        );
    }

    private static void printGiveaway(BenefitsResponse benefitsResponse) {
        String format = "<증정 메뉴>%n%s%n%n";
        if (benefitsResponse.haveGiveaway()) {
            System.out.printf(format, "샴페인 1개");
            return;
        }
        System.out.printf(format, "없음");
    }

    private static void printBenefits(BenefitsResponse benefitsResponse) {
        System.out.printf(
                "<혜택 내역>%n%s%n%n",
                formatBenefits(benefitsResponse)
        );
    }

    private static String formatBenefits(BenefitsResponse benefitsResponse) {
        if (benefitsResponse.benefits().isEmpty()) {
            return "없음";
        }
        return benefitsResponse.benefits().stream()
                .map(benefitResponse ->
                        String.format("%s: -%,d원", benefitResponse.eventName(), benefitResponse.discountAmount()))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private static void printDiscountValue(PaymentResponse paymentResponse) {
        System.out.printf(
                "<총혜택 금액>%n%s%n%n",
                formatDiscountValue(paymentResponse)
        );
    }

    private static String formatDiscountValue(PaymentResponse paymentResponse) {
        if (paymentResponse.discountValue() == 0) {
            return "0원";
        }
        return String.format("-%,d원", paymentResponse.discountValue());
    }

    private static void printFinalValue(PaymentResponse paymentResponse) {
        System.out.printf(
                "<할인 후 예상 결제 금액>%n%,d원%n%n",
                paymentResponse.finalValue()
        );
    }

    private static void printBadge(BadgeResponse badgeResponse) {
        System.out.printf("<12월 이벤트 배지>%n%s", badgeResponse.name());
    }
}
