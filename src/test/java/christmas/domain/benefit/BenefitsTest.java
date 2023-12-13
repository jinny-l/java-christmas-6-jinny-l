package christmas.domain.benefit;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.benefit.domain.Benefit;
import christmas.benefit.domain.Benefits;
import christmas.event.domain.Event;
import christmas.event.repository.EventRepository;
import christmas.fixture.PlanFixture;
import christmas.plan.domain.Plan;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[단위 테스트][Domain] Benefits")
class BenefitsTest {

    @DisplayName("혜택 금액을 정확히 계산한다.")
    @Test
    void calculateDiscountValue() {
        // given
        Plan plan = PlanFixture.디데이O_별X_평일_메인_주문_12만원_이하.create();
        Benefits benefits = createBenefits(plan);

        int expected = plan.eventDate().discountAmount();

        // when
        int actual = benefits.calculateTotalDiscountValue();

        // then
        assertThat(actual).isEqualTo(expected);
    }


    @DisplayName("증정 이벤트의 혜택 금액을 정확히 계산한다.")
    @Test
    void calculateGiveawayDiscountValue() {
        // given
        Plan plan = PlanFixture.디데이O_별X_평일_메인_주문_12만원_이상.create();
        Benefits benefits = createBenefits(plan);

        // when
        int actual = benefits.calculateGiveawayDiscountValue();

        // then
        assertThat(actual).isEqualTo(25000); // 임시로 하드코딩
    }

    @DisplayName("증정 이벤트에 해당할 경우 참을 반환한다.")
    @Test
    void haveGiveaway_true() {
        // given
        Benefits benefits = createBenefits(
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
        Benefits benefits = createBenefits(
                PlanFixture.디데이O_별X_평일_메인_주문_12만원_이하.create()
        );

        // when
        boolean actual = benefits.haveGiveaway();

        // then
        assertThat(actual).isFalse();
    }

    private Benefits createBenefits(Plan plan) { // Service 쪽 코드 그대로 복붙한거라 나중에 리팩토링 필요
        List<Event> events = EventRepository.findAll();

        List<Benefit> benefits = events.stream()
                .map(event -> Benefit.from(event, plan))
                .toList();

        return new Benefits(benefits);
    }
}
