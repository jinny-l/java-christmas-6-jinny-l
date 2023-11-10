package christmas.order.domain;

public enum Menu {

    MUSHROOM_SOUP("양송이수프", 6000, Type.APPETIZER),
    TAPAS("타파스", 5500, Type.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8000, Type.APPETIZER),
    T_BONE_STEAK("티본스테이크", 55000, Type.MAIN),
    BBQ_RIBS("바비큐립", 54000, Type.MAIN),
    SEAFOOD_PASTA("해산물파스타", 35000, Type.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, Type.MAIN),
    CHOCO_CAKE("초코케이크", 15000, Type.DESSERT),
    ICE_CREAM("아이스크림", 5000, Type.DESSERT),
    ZERO_COLA("제로콜라", 3000, Type.DRINK),
    RED_WINE("레드와인", 60000, Type.DRINK),
    CHAMPAGNE("샴페인", 25000, Type.DRINK);

    private final String name;
    private final int price;
    private final Type type;

    Menu(String name, int price, Type type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }
}

