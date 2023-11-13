package christmas.acceptance;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.Application;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[인수 테스트] 입력 예외 상황")
public class ExceptionTest extends NsTest {

    @Test
    void 방문_날짜가_1_이상_31_이하의_숫자가_아닌_경우_에러_메시지_출력() {
        assertSimpleTest(() -> {
            runException("0");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 메뉴판에_없는_메뉴를_입력하는_경우_에러_메시지_출력() {
        assertSimpleTest(() -> {
            runException("1", "이상한메뉴-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 메뉴_개수가_1개_미만일_경우_에러_메시지_출력() {
        assertSimpleTest(() -> {
            runException("1", "타파스-0");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 메뉴_형식이_맞지_않을_경우1_에러_메시지_출력() {
        assertSimpleTest(() -> {
            runException("1", "타파스:1,제로콜라:1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 메뉴_형식이_맞지_않을_경우2_에러_메시지_출력() {
        assertSimpleTest(() -> {
            runException("1", "타파스:1-제로콜라:1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 메뉴_형식이_맞지_않을_경우3_에러_메시지_출력() {
        assertSimpleTest(() -> {
            runException("1", "타파스,1,제로콜라,1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 메뉴_형식이_맞지_않을_경우4_에러_메시지_출력() {
        assertSimpleTest(() -> {
            runException("1", "타파스-");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 중복_메뉴를_입력한_경우_에러_메시지_출력() {
        assertSimpleTest(() -> {
            runException("1", "타파스-1,타파스-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
