package christmas.event.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import christmas.date.domain.EventDate;
import christmas.date.repository.EventDateRepository;
import christmas.date.service.EventDateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("[단위 테스트][Service] EventDate")
class EventDateServiceTest {

    private EventDateService eventDateService;

    @BeforeEach
    void setUp() {
        eventDateService = new EventDateService(new EventDateRepository());
    }

    @DisplayName("별이 없는 날짜로 조회 시 정상적으로 조회되며 hasStar 값이 거짓이다.")
    @ParameterizedTest
    @ValueSource(ints = {
            1, 2, 4, 5, 6, 7, 8, 9,
            11, 12, 13, 14, 15, 16, 18, 19,
            20, 21, 22, 23, 26, 27, 28, 29, 30
    })
    void findEventDay_hasStar_false(int input) {
        // when
        EventDate eventDate = eventDateService.findEventDay(input);

        // then
        assertAll(
                () -> assertThat(eventDate.isSameDay(input)).isTrue(),
                () -> assertThat(eventDate.hasStar()).isFalse()
        );
    }

    @DisplayName("별이 있는 날짜로 조회 시 정상적으로 조회되며 hasStar 값이 참이다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 17, 24, 25, 31})
    void findEventDay_hasStar_true(int input) {
        // when
        EventDate eventDate = eventDateService.findEventDay(input);

        // then
        assertAll(
                () -> assertThat(eventDate.isSameDay(input)).isTrue(),
                () -> assertThat(eventDate.hasStar()).isTrue()
        );
    }

    @DisplayName("12월에 없는 날짜로 조회 시 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 32})
    void findEventDay_invalidDay_ExceptionThrown(int input) {
        // when & then
        assertThatThrownBy(() -> eventDateService.findEventDay(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
