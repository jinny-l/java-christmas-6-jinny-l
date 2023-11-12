package christmas.order.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("[단위 테스트][Domain] Order")
class OrderTest {

    @DisplayName("메뉴 개수가 1개 이상이면 Order가 정상적으로 생성된다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 20})
    void newOrder(int input) {
        // given
        Menu menu = Menu.CAESAR_SALAD;

        // when
        Order actual = new Order(menu, input);

        // then
        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> assertThat(actual.menu()).isEqualTo(menu),
                () -> assertThat(actual.amount()).isEqualTo(input)
        );
    }

    @DisplayName("메뉴 개수가 1미만이면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void newOrder_invalidMenuAmount_ExceptionThrown(int input) {
        // when & then
        assertThatThrownBy(() -> new Order(Menu.CHOCO_CAKE, input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
