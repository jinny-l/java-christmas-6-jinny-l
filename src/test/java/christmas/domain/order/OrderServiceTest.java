package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.global.config.AppConfig;
import christmas.order.domain.Orders;
import christmas.order.dto.OrdersRequest;
import christmas.order.exception.OrderError;
import christmas.order.service.OrderService;
import java.util.Collections;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[단위 테스트][Service] OrderService")
class OrderServiceTest {

    private final OrderService orderService = AppConfig.getInstance().orderService();

    @DisplayName("정상적인 상황에서 주문이 가능하다.")
    @Test
    void order() {
        // given
        OrdersRequest request = OrdersRequest.from(
                Map.of(
                        "바비큐립", 5,
                        "레드와인", 1
                )
        );

        // when
        Orders orders = orderService.createOrders(request);

        // then
        assertThat(orders).isNotNull();
    }

    @DisplayName("주문 개수가 21개 이상이면 예외가 발생한다.")
    @Test
    void order_moreThan20_ExceptionThrown() {
        // given
        OrdersRequest request = OrdersRequest.from(
                Collections.singletonMap("바비큐립", 21)
        );

        // when & then
        assertThatThrownBy(() -> orderService.createOrders(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(OrderError.EXCEEDED_MAX_ORDER_AMOUNT.getMessage());
    }

    @DisplayName("모든 주문이 음료면 예외가 발생한다.")
    @Test
    void order_allDrink_ExceptionThrown() {
        // given
        OrdersRequest request = OrdersRequest.from(
                Map.of(
                        "제로콜라", 1,
                        "샴페인", 1
                )
        );

        // when & then
        assertThatThrownBy(() -> orderService.createOrders(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(OrderError.ALL_DRINK_ORDER.getMessage());
    }
}
