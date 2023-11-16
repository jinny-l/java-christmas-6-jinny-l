package christmas.domain.date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import christmas.date.domain.DayType;
import christmas.date.domain.EventDate;
import christmas.fixture.EventDateFixture;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[단위 테스트][Domain] EventDate")
public class EventDateTest {

    @DisplayName("CSV 데이터에서 도메인을 매핑할 수 있다.")
    @Test
    void parseEventDaysFromCSV() {
        // given
        List<String> data = Collections.singletonList(
                "2023-12-03,1200,true"
        );

        // when
        List<EventDate> eventDates = EventDate.parseEventDaysFromCSV(data);
        EventDate eventDate = eventDates.get(0);

        // then
        assertAll(
                () -> assertThat(eventDates.size()).isEqualTo(1),
                () -> assertThat(eventDate.date()).isEqualTo(LocalDate.of(2023, 12, 3)),
                () -> assertThat(eventDate.discountAmount()).isEqualTo(1200),
                () -> assertThat(eventDate.hasStar()).isTrue(),
                () -> assertThat(eventDate.dayType()).isEqualTo(DayType.WEEKDAY)
        );
    }

    @DisplayName("같은 일자일 경우 참을 반환한다.")
    @Test
    void isSameDay_true() {
        // given
        EventDate eventDate = EventDateFixture.디데이O_별X_주말_1일.create();

        // when
        boolean actual = eventDate.isSameDay(1);

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("같은 일자가 아닐 경우 거짓을 반환한다.")
    @Test
    void isSameDay_false() {
        // given
        EventDate eventDate = EventDateFixture.디데이O_별X_주말_1일.create();

        // when
        boolean actual = eventDate.isSameDay(2);

        // then
        assertThat(actual).isFalse();
    }
}
