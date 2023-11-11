package christmas.plan.order.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Orders {

    private final List<Order> menus;
    private final int totalValue;

    public Orders(List<Order> menus) {
        validateDuplicateMenu(menus);
        this.menus = menus;
        totalValue = calculateTotalValue(menus);
    }

    private void validateDuplicateMenu(List<Order> menus) {
        Set<Menu> uniqueMenus = new HashSet<>();
        if (menus.stream()
                .map(Order::menu)
                .anyMatch(menu -> !uniqueMenus.add(menu))) {
            throw new IllegalArgumentException();
        }
    }

    public List<Order> getMenus() {
        return menus;
    }

    public int getTotalValue() {
        return totalValue;
    }

    private int calculateTotalValue(List<Order> menus) {
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

    public int totalAmount() {
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
