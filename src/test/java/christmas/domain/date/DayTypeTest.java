package christmas.domain.date;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.date.domain.DayType;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("[단위 테스트][Domain] DayType")
public class DayTypeTest {

    @DisplayName("일요일~목요일일 경우 평일을 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7})
    void createDayType_weekday(int dayOfMonth) {
        // given
        LocalDate localDate = LocalDate.of(2023, 12, dayOfMonth);

        // when
        DayType dayType = DayType.from(localDate);

        // then
        assertThat(dayType).isEqualTo(DayType.WEEKDAY);
    }

    @DisplayName("금요일, 토요일일 경우 주말을 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {8, 9})
    void createDayType_weekend(int dayOfMonth) {
        // given
        LocalDate localDate = LocalDate.of(2023, 12, dayOfMonth);

        // when
        DayType dayType = DayType.from(localDate);

        // then
        assertThat(dayType).isEqualTo(DayType.WEEKEND);
    }
}
