package christmas.domain.order;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record Orders(
        List<Order> orders
) {
    public Orders {
        validateAmount(orders);
        validateDuplicate(orders);
        validateMain(orders);
    }

    public static Orders from(List<List<String>> input) {
        return new Orders(
                input.stream()
                        .map(Order::from)
                        .toList()
        );
    }

    private void validateAmount(List<Order> orders) {
        if (orders.stream()
                .mapToInt(Order::amount)
                .sum() > 20) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateDuplicate(List<Order> orders) {
        Map<Menu, Long> menuCountMap = orders.stream()
                .collect(Collectors.groupingBy(Order::menu, Collectors.counting()));

        if (menuCountMap.entrySet().stream()
                .anyMatch(entry -> entry.getValue() > 1)) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateMain(List<Order> orders) {
        if (orders.stream()
                .allMatch(Order::isMain)) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
