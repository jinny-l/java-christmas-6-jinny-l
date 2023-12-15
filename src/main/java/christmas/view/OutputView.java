package christmas.view;

import christmas.domain.benfit.Benefits;
import christmas.domain.date.EventDate;
import christmas.domain.order.Menu;
import christmas.domain.order.Orders;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    private OutputView() {
    }

    public static void printError(Exception e) {
        System.out.printf("%s%s%n", ERROR_MESSAGE_PREFIX, e.getMessage());
    }

    public static void printInfoPrefix(EventDate eventDate) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n", eventDate.localDate().getDayOfMonth());
    }

    public static void printOrders(Orders orders) {
        System.out.println();
        System.out.println("<주문 메뉴>");

        String text = orders.orders().stream()
                .map(order -> String.format("%s %d개", order.menu().getName(), order.amount()))
                .collect(Collectors.joining(System.lineSeparator()));
        System.out.println(text);
    }

    public static void printOrdersTotalValue(Orders orders) {
        System.out.println();
        System.out.println("<할인 전 총주문 금액>");

        System.out.printf("%,d원", orders.getTotalValue());
    }

    public static void printGiveaways(Map<Menu, Integer> giveaways) {
        System.out.println();
        System.out.println();
        System.out.println("<증정 메뉴>");

        if (giveaways.isEmpty()) {
            System.out.println("없음");
            return;
        }

        String text = giveaways.entrySet().stream()
                .map(entry -> String.format("%s %d개", entry.getKey().getName(), entry.getValue()))
                .collect(Collectors.joining(System.lineSeparator()));
        System.out.println(text);
    }

    public static void printBenefits(Benefits benefits) {
        System.out.println();
        System.out.println("<혜택 내역>");

        String text = benefits.benefits().stream()
                .filter(benefit -> benefit.discountValue() != 0)
                .map(benefit -> String.format("%s: -%,d원", benefit.event().getName(), benefit.discountValue()))
                .collect(Collectors.joining(System.lineSeparator()));

        if (text.isBlank()) {
            System.out.println("없음");
            return;
        }
        System.out.println(text);
    }

    public static void printDiscountValue(Benefits benefits) {
        System.out.println();
        System.out.println("<총혜택 금액>");

        int discountValue = benefits.getTotalDiscountValue();
        if (discountValue == 0) {
            System.out.println("0원");
            return;
        }

        System.out.printf("-%,d원%n", discountValue);
    }

    public static void printFinalValue(Orders orders, Benefits benefits) {
        System.out.println();
        System.out.println("<할인 후 예상 결제 금액>");

        System.out.printf("%,d원%n", orders.getTotalValue() - benefits.getTotalDiscountValue() + benefits.getGiveawayDiscountValue());
    }
}
