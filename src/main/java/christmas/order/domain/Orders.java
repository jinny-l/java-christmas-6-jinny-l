package christmas.order.domain;

import christmas.global.exception.ChristmasPromotionException;
import christmas.order.exception.OrderError;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public record Orders(List<Order> menus) {

    public Orders {
        validateDuplicateMenu(menus);
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

    public boolean isAllDrink() {
        return menus.size() == countByDrink();
    }

    private int countByDrink() {
        return (int) menus.stream()
                .filter(Order::isDrink)
                .count();
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
