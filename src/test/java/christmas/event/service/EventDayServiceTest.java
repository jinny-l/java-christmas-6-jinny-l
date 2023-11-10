package christmas.event.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import christmas.event.domain.EventDay;
import christmas.event.dto.VisitDayRequest;
import christmas.event.repository.EventDayRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("[Service 단위 테스트] 이벤트 일정")
class EventDayServiceTest {

    private EventDayService eventDayService;

    @BeforeEach
    void setUp() {
        eventDayService = new EventDayService(new EventDayRepository());
    }

    @DisplayName("별이 없는 날짜로 조회 시 정상적으로 조회되며 hasStar 값이 거짓이다.")
    @ParameterizedTest
    @ValueSource(ints = {
            1, 2, 4, 5, 6, 7, 8, 9,
            11, 12, 13, 14, 15, 16, 18, 19,
            20, 21, 22, 23, 26, 27, 28, 29, 30
    })
    void findEventDay_hasStar_false(int input) {
        // given
        VisitDayRequest visitDayRequest = new VisitDayRequest(input);

        // when
        EventDay eventDay = eventDayService.findEventDay(visitDayRequest);

        // then
        assertAll(
                () -> assertThat(eventDay.isSameDay(input)).isTrue(),
                () -> assertThat(eventDay.hasStar()).isFalse()
        );
    }

    @DisplayName("별이 있는 날짜로 조회 시 정상적으로 조회되며 hasStar 값이 참이다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 17, 24, 25, 31})
    void findEventDay_hasStar_true(int input) {
        // given
        VisitDayRequest visitDayRequest = new VisitDayRequest(input);

        // when
        EventDay eventDay = eventDayService.findEventDay(visitDayRequest);

        // then
        assertAll(
                () -> assertThat(eventDay.isSameDay(input)).isTrue(),
                () -> assertThat(eventDay.hasStar()).isTrue()
        );
    }

    @DisplayName("12월에 없는 날짜로 조회 시 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 32})
    void findEventDay_invalidDay_ExceptionThrown(int input) {
        // given
        VisitDayRequest visitDayRequest = new VisitDayRequest(input);

        // when & then
        assertThatThrownBy(() -> eventDayService.findEventDay(visitDayRequest))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
