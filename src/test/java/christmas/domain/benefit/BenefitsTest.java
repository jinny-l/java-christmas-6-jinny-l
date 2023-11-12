package christmas.domain.benefit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
        Plan plan = PlanFixture.WEEKDAY_NO_STAR_WITH_DDAY_AND_NO_GIVEAWAY.create();
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
        Plan plan = PlanFixture.WEEKDAY_NO_STAR_WITH_DDAY_AND_GIVEAWAY.create();
        Benefits benefits = Benefits.from(plan);
        int expected = Event.GIVEAWAY.calculateDiscountAmount(plan);

        // when
        int actual = benefits.calculateGiveawayDiscountValue();

        // then
        assertThat(actual).isEqualTo(expected);
    }

}
