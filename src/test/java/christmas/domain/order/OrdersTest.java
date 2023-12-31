package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import christmas.fixture.OrdersAmountFixture;
import christmas.fixture.OrdersFixture;
import christmas.order.domain.Menu;
import christmas.order.domain.Order;
import christmas.order.domain.Orders;
import christmas.order.exception.OrderError;
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
                () -> assertThat(actual.calculateTotalAmount()).isEqualTo(20)
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
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(OrderError.DUPLICATE_MENU.getMessage());
    }

    @DisplayName("주문이 모두 음료 메뉴이면 예외가 발생한다.")
    @Test
    void newOrders_moreThan20_ExceptionThrown() {
        // when & then
        assertThatThrownBy(OrdersFixture.음료_주문::create)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(OrderError.ALL_DRINK_ORDER.getMessage());
    }

    @DisplayName("주문 개수가 21개 이상이면 예외가 발생한다.")
    @Test
    void newOrders_allDrink_ExceptionThrown() {
        // when & then
        assertThatThrownBy(() -> OrdersAmountFixture.디저트_초코케이크_주문.create(21))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(OrderError.EXCEEDED_MAX_ORDER_AMOUNT.getMessage());
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
        int actual = orders.calculateTotalAmount();

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
        int actual = orders.calculateTotalValue();

        // then
        assertThat(actual).isEqualTo(Menu.RED_WINE.getPrice() + Menu.CHRISTMAS_PASTA.getPrice());
    }
}
