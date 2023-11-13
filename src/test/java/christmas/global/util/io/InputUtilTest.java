package christmas.global.util.io;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.InputView;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("[단위 테스트] Input")
class InputUtilTest {

    @AfterEach
    void closeConsole() {
        Console.close();
    }

    @DisplayName("공백으로 입력할 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {" ", "      ", "\n", "\r\n", "\r", "\t"})
    void throw_exception_when_input_is_blank(String input) {
        // given
        System.setIn(createInputStream(input));

        // when & then
        assertThatThrownBy(InputView::readVisitDay)
                .isInstanceOf(IllegalArgumentException.class);
    }

    private InputStream createInputStream(String input) {
        return new ByteArrayInputStream(input.getBytes());
    }
}
