package christmas.order.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.plan.order.domain.Orders;
import christmas.plan.order.dto.OrdersRequest;
import christmas.plan.order.service.OrderService;
import java.util.Collections;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[단위 테스트][Service] Order")
class OrderServiceTest {

    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService();
    }

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
        Orders orders = orderService.order(request);

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
        assertThatThrownBy(() -> orderService.order(request))
                .isInstanceOf(IllegalArgumentException.class);
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
        assertThatThrownBy(() -> orderService.order(request))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
