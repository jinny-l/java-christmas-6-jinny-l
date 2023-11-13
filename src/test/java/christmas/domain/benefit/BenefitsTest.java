package christmas.domain.benefit;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.benefit.domain.Benefits;
import christmas.benefit.domain.Event;
import christmas.fixture.PlanFixture;
import christmas.plan.domain.Plan;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[단위 테스트][Domain] Benefits")
class BenefitsTest {

    @DisplayName("혜택 금액을 정확히 계산한다.")
    @Test
    void calculateDiscountValue() {
        // given
        Plan plan = PlanFixture.디데이O_별X_평일_메인_주문_12만원_이하.create();
        Benefits benefits = Benefits.from(plan);

        int expected = plan.eventDate().discountAmount();

        // when
        int actual = benefits.calculateDiscountValue();

        // then
        assertThat(actual).isEqualTo(expected);
    }


    @DisplayName("증정 이벤트의 혜택 금액을 정확히 계산한다.")
    @Test
    void calculateGiveawayDiscountValue() {
        // given
        Plan plan = PlanFixture.디데이O_별X_평일_메인_주문_12만원_이상.create();
        Benefits benefits = Benefits.from(plan);
        int expected = Event.GIVEAWAY.calculateDiscountValue(plan);

        // when
        int actual = benefits.calculateGiveawayDiscountValue();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("증정 이벤트에 해당할 경우 참을 반환한다.")
    @Test
    void haveGiveaway_true() {
        // given
        Benefits benefits = Benefits.from(
                PlanFixture.디데이O_별X_평일_메인_주문_12만원_이상.create()
        );

        // when
        boolean actual = benefits.haveGiveaway();

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("증정 이벤트에 해당하지 않을 경우 거짓을 반환한다.")
    @Test
    void haveGiveaway_false() {
        // given
        Benefits benefits = Benefits.from(
                PlanFixture.디데이O_별X_평일_메인_주문_12만원_이하.create()
        );

        // when
        boolean actual = benefits.haveGiveaway();

        // then
        assertThat(actual).isFalse();
    }
}
