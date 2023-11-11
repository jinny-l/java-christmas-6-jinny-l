package christmas.order.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("[단위 테스트][Domain] Orders")
class OrdersTest {

    @DisplayName("정상적인 상황에서 Orders가 생성된다.")
    @Test
    void newOrders() {
        // given
        List<Order> orders = List.of(
                new Order(Menu.CHOCO_CAKE, 1),
                new Order(Menu.BBQ_RIBS,19)
        );

        // when
        Orders actual = new Orders(orders);

        // then
        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> assertThat(actual.totalAmount()).isEqualTo(20)
        );
    }

    @DisplayName("중복된 메뉴가 있으면 예외가 발생한다.")
    @Test
    void newOrders_duplicateMenu_ExceptionThrown() {
        // given
        Menu menu = Menu.BBQ_RIBS;
        List<Order> menus = List.of(
                new Order(menu, 1),
                new Order(menu, 1),
                new Order(menu, 2)
        );

        // when & then
        assertThatThrownBy(() -> new Orders(menus))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문이 모두 음료 메뉴인지 정확히 판단한다.")
    @Test
    void isAllDrink() {
        // given
        Order wine = new Order(Menu.RED_WINE, 1);
        Order coke = new Order(Menu.ZERO_COLA, 1);
        Order pasta = new Order(Menu.CHRISTMAS_PASTA, 1);

        Orders drinks = new Orders(List.of(wine, coke));
        Orders foodAndDrinks = new Orders(List.of(wine, coke, pasta));

        // when & then
        assertAll(
                () -> assertThat(drinks.isAllDrink()).isTrue(),
                () -> assertThat(foodAndDrinks.isAllDrink()).isFalse()
        );
    }

    @DisplayName("총 주문 개수를 정확히 계산한다.")
    @ParameterizedTest
    @CsvSource(value = {"1, 10, 11", "10, 10, 20"})
    void totalAmount(int amount1, int amount2, int expected) {
        // given
        List<Order> menus = List.of(
                new Order(Menu.RED_WINE, amount1),
                new Order(Menu.CHRISTMAS_PASTA, amount2)
        );

        Orders orders = new Orders(menus);

        // when
        int actual = orders.totalAmount();


        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("총 주문 금액을 정확히 계산한다.")
    @Test
    void getTotalValue() {
        // given
        List<Order> menus = List.of(
                new Order(Menu.RED_WINE, 1),
                new Order(Menu.CHRISTMAS_PASTA, 1)
        );

        Orders orders = new Orders(menus);

        // when
        int actual = orders.getTotalValue();


        // then
        assertThat(actual).isEqualTo(Menu.RED_WINE.getPrice() + Menu.CHRISTMAS_PASTA.getPrice());
    }
}
