package christmas.order.domain;

public record Order(
        Menu menu,
        int amount
) {

    private static final int AMOUNT_MIN_VALUE = 1;

    public Order {
        validateAmount(amount);
    }

    private void validateAmount(int amount) {
        if (amount < AMOUNT_MIN_VALUE) {
            throw new IllegalArgumentException();
        }
    }

    public boolean isMain() {
        return menu.getType() == Type.MAIN;
    }

    public boolean isDessert() {
        return menu.getType() == Type.DESSERT;
    }
    public boolean isDrink() {
        return menu.getType() == Type.DRINK;
    }
}
