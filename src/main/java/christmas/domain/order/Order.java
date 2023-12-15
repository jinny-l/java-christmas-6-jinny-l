package christmas.domain.order;

import java.util.List;

public record Order(
        Menu menu,
        int amount
) {
    public Order(Menu menu, int amount) {
        this.menu = menu;
        validateAmount(amount);
        this.amount = amount;
    }

    public static Order from(List<String> input) {
        return new Order(
                Menu.from(input.get(0).strip()), // TODO Index out of bounds / Number format 에러 처리 필요
                Integer.parseInt(input.get(1).strip())  //
        );
    }

    public boolean isMain() { // TODO 밑에 메서드로 리팩토링
        return menu.getCategory() == Category.MAIN_COURSE;
    }

    public boolean isSameCategory(Category category) {
        return menu.getCategory() == category;
    }

    private void validateAmount(int amount) {
        if (amount < 1 || amount > 20) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
