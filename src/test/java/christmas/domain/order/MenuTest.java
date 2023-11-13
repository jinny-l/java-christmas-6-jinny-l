package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.order.domain.Menu;
import christmas.order.exception.OrderError;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("[단위 테스트][Domain] Menu")
class MenuTest {

    @DisplayName("메뉴에 없는 이름일 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"양송이스프", "비비큐립", "오타"})
    void createMenu(String name) {
        // when & then
        assertThatThrownBy(() -> Menu.from(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(OrderError.INVALID_MENU.getMessage());
    }
}
