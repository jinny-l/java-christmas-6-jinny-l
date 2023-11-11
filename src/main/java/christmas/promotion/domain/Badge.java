package christmas.promotion.domain;

import java.util.Arrays;
import java.util.Comparator;

public enum Badge {

    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000),
    DEFAULT("없음", 0);

    private final String name;
    private final int discountValue;

    Badge(String name, int discountValue) {
        this.name = name;
        this.discountValue = discountValue;
    }

    public static Badge from(int discountValue) {
        return Arrays.stream(Badge.values())
                .filter(badge -> discountValue >= badge.discountValue)
                .max(Comparator.comparingInt(Badge::getDiscountValue))
                .orElse(DEFAULT);
    }

    public String getName() {
        return name;
    }

    public int getDiscountValue() {
        return discountValue;
    }
}
