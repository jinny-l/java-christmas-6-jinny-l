package christmas.order.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public record Orders(
        List<Order> menus
) {

    public Orders {
        validateDuplicateMenu(menus);
    }

    private void validateDuplicateMenu(List<Order> menus) {
        Set<Menu> uniqueMenus = new HashSet<>();
        if (menus.stream()
                .map(Order::menu)
                .anyMatch(menu -> !uniqueMenus.add(menu))) {
            throw new IllegalArgumentException();
        }
    }

    public boolean isAllDrink() {
        return menus.size() == countByDrink();
    }

    private int countByDrink() {
        return (int) menus.stream()
                .filter(Order::isDrink)
                .count();
    }

    public int totalAmount() {
        return menus.stream()
                .mapToInt(Order::amount)
                .sum();
    }
}
