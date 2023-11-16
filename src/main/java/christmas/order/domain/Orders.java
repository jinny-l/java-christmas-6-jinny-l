package christmas.order.domain;

import christmas.global.exception.ChristmasPromotionException;
import christmas.order.exception.OrderError;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public record Orders(
        List<Order> menus
) {

    private static final int ORDER_AMOUNT_MAX_VALUE = 20;

    public Orders {
        validateOrderAmount(menus);
        validateOrderFood(menus);
        validateDuplicateMenu(menus);
    }

    private void validateOrderAmount(List<Order> orders) {
        if (orders.stream()
                .mapToInt(Order::amount)
                .sum() > ORDER_AMOUNT_MAX_VALUE) {
            throw new ChristmasPromotionException(OrderError.EXCEEDED_MAX_ORDER_AMOUNT);
        }
    }

    private void validateOrderFood(List<Order> orders) {
        if (orders.stream().allMatch(Order::isDrink)) {
            throw new ChristmasPromotionException(OrderError.ALL_DRINK_ORDER);
        }
    }

    private void validateDuplicateMenu(List<Order> menus) {
        Set<Menu> uniqueMenus = new HashSet<>();
        if (menus.stream()
                .map(Order::menu)
                .anyMatch(menu -> !uniqueMenus.add(menu))) {
            throw new ChristmasPromotionException(OrderError.DUPLICATE_MENU);
        }
    }

    public int calculateTotalValue() {
        return menus.stream()
                .mapToInt(menu -> menu.menu().getPrice() * menu.amount())
                .sum();
    }

    public int calculateTotalAmount() {
        return menus.stream()
                .mapToInt(Order::amount)
                .sum();
    }

    public int countAmountByDessertMenu() {
        return menus.stream()
                .filter(Order::isDessert)
                .mapToInt(Order::amount)
                .sum();
    }

    public int countAmountByMainMenu() {
        return menus.stream()
                .filter(Order::isMain)
                .mapToInt(Order::amount)
                .sum();
    }
}
