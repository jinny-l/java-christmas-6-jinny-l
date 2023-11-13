package christmas.domain.promotion;

import christmas.promotion.domain.Badge;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("[단위 테스트][Domain] Badge")
public class BadgeTest {

    @DisplayName("배지 조건을 만족하지 못했을 때 디폴트 배지가 생성된다.")
    @Test
    void badge_default() {
        // given
        int discountValue = 3000;

        // when
        Badge actual = Badge.from(discountValue);

        // then
        Assertions.assertThat(actual).isEqualTo(Badge.DEFAULT);
    }

    @DisplayName("별 배지 조건을 달성했을 경우 별 배지가 생성된다.")
    @ParameterizedTest
    @ValueSource(ints = {5000, 5500, 8000})
    void badge_star(int discountValue) {
        // when
        Badge actual = Badge.from(discountValue);

        // then
        Assertions.assertThat(actual).isEqualTo(Badge.STAR);
    }

    @DisplayName("트리 배지 조건을 달성했을 경우 트리 배지가 생성된다.")
    @ParameterizedTest
    @ValueSource(ints = {10000, 15000, 18000})
    void badge_tree(int discountValue) {
        // when
        Badge actual = Badge.from(discountValue);

        // then
        Assertions.assertThat(actual).isEqualTo(Badge.TREE);
    }

    @DisplayName("산타 배지 조건을 달성했을 경우 산타 배지가 생성된다.")
    @ParameterizedTest
    @ValueSource(ints = {20000, 25000, 35000})
    void badge_santa(int discountValue) {
        // when
        Badge actual = Badge.from(discountValue);

        // then
        Assertions.assertThat(actual).isEqualTo(Badge.SANTA);
    }
}
