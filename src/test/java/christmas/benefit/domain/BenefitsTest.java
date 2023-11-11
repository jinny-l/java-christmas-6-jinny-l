package christmas.benefit.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import christmas.fixture.PlanFixture;
import christmas.plan.domain.Plan;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[단위 테스트][Domain] Benefits")
class BenefitsTest {

    @DisplayName("증정 이벤트에 해당할 때 혜택 금액을 정확히 계산한다.")
    @Test
    void calculateTotalDiscountAmountWithGiveawayEvent() {
        // given
        Plan plan = PlanFixture.WEEKDAY_NO_STAR_WITH_DDAY_AND_GIVEAWAY.create();
        Benefits benefits = Benefits.from(plan);
        int expectedWithGiveawayEvent = plan.eventDate().discountAmount() + 25000;
        int expectedWithoutGiveawayEvent = plan.eventDate().discountAmount();

        // when
        int discountAmountWithGiveawayEvent = benefits.calculateTotalDiscountAmountWithGiveawayEvent();
        int discountAmountWithoutGiveawayEvent = benefits.calculateTotalDiscountAmountWithoutGiveawayEvent();

        // then
        assertAll(
                () -> assertThat(discountAmountWithGiveawayEvent).isEqualTo(expectedWithGiveawayEvent),
                () -> assertThat(discountAmountWithoutGiveawayEvent).isEqualTo(expectedWithoutGiveawayEvent)
        );
    }


    @DisplayName("증정 이벤트에 해당하지 않을 때 혜택 금액을 정확히 계산한다.")
    @Test
    void calculateTotalDiscountAmountWithoutGiveawayEvent() {
        // given
        Plan plan = PlanFixture.WEEKDAY_NO_STAR_WITH_DDAY_AND_NO_GIVEAWAY.create();
        Benefits benefits = Benefits.from(plan);
        int expected = plan.eventDate().discountAmount();

        // when
        int discountAmountWithGiveawayEvent = benefits.calculateTotalDiscountAmountWithGiveawayEvent();
        int discountAmountWithoutGiveawayEvent = benefits.calculateTotalDiscountAmountWithoutGiveawayEvent();

        // then
        assertAll(
                () -> assertThat(discountAmountWithGiveawayEvent).isEqualTo(expected),
                () -> assertThat(discountAmountWithoutGiveawayEvent).isEqualTo(expected)
        );
    }

}
