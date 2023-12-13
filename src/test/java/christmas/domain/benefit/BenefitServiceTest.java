package christmas.domain.benefit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import christmas.benefit.domain.Benefit;
import christmas.benefit.domain.Benefits;
import christmas.benefit.service.BenefitService;
import christmas.date.domain.EventDate;
import christmas.event.domain.ChristmasEvent;
import christmas.event.domain.DecemberGiveawayEvent;
import christmas.event.domain.StarDayEvent;
import christmas.event.domain.WeekdayEvent;
import christmas.event.domain.WeekendEvent;
import christmas.fixture.EventDateFixture;
import christmas.fixture.OrdersAmountFixture;
import christmas.fixture.OrdersFixture;
import christmas.global.config.AppConfig;
import christmas.order.domain.Orders;
import christmas.plan.domain.Plan;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[단위 테스트][Service] BenefitService")
class BenefitServiceTest {

    private final BenefitService benefitService = AppConfig.getInstance().benefitService();

    @DisplayName("최소 주문 금액을 만족하지 못했을 경우 할인 금액을 정확히 계산한다.")
    @Test
    void calculateEventDiscountValue_notSatisfiedCondition() {
        // given
        Plan plan = new Plan(
                EventDateFixture.디데이O_별X_평일_4일.create(),
                OrdersFixture.만원_이하_주문.create()
        );

        // when
        Benefits benefits = benefitService.calculateBenefit(plan);

        List<Integer> actual = benefits.benefits().stream()
                .map(Benefit::discountValue)
                .toList();

        // then
        assertThat(actual).contains(0, 0, 0, 0, 0);
    }

    @DisplayName("크리스마스 디데이 할인 이벤트에 해당할 경우 할인 금액을 정확히 계산한다.")
    @Test
    void calculateDDayEventDiscountValue_isDDayEventDate() {
        // given
        EventDate eventDate = EventDateFixture.디데이O_별X_평일_4일.create();
        Plan plan = new Plan(
                eventDate,
                OrdersFixture.메인_주문_12만원_이하.create()
        );
        ChristmasEvent event = new ChristmasEvent();

        // when
        int actual = event.discount(plan, 10000);

        // then
        assertThat(actual).isEqualTo(1300);
    }

    @DisplayName("크리스마스 디데이 할인 이벤트에 해당하지 않을 경우 할인 금액을 정확히 계산한다.")
    @Test
    void calculateDDayEventDiscountValue_isNotDDayEventDate() {
        // given
        Plan plan = new Plan(
                EventDateFixture.디데이X_별X_평일.create(),
                OrdersFixture.메인_주문_12만원_이하.create()
        );
        christmas.event.domain.Event event = new ChristmasEvent();

        // when
        int actual = event.discount(plan, 10000);

        // then
        assertThat(actual).isZero();
    }

    @DisplayName("평일 할인 이벤트의 할인 금액을 정확히 계산한다.")
    @Test
    void calculateWeekdayEventDiscountValue() {
        // given
        EventDate weekdayEventDate = EventDateFixture.디데이O_별X_평일_4일.create();
        EventDate weekendEventDate = EventDateFixture.디데이O_별X_주말_1일.create();
        Orders orders = OrdersAmountFixture.디저트_초코케이크_주문.create(1);

        Plan weekdayPlan = new Plan(weekdayEventDate, orders);
        Plan weekendPlan = new Plan(weekendEventDate, orders);

        WeekdayEvent weekdayEvent = new WeekdayEvent("평일 할인");
        WeekendEvent weekendEvent = new WeekendEvent("주말 할인");

        // when
        int weekdayPlanDiscountValue = weekdayEvent.discount(weekdayPlan, 10000);
        int weekendPlanDiscountValue = weekendEvent.discount(weekendPlan, 10000);

        // then
        assertAll(
                () -> assertThat(weekdayPlanDiscountValue).isEqualTo(2023),
                () -> assertThat(weekendPlanDiscountValue).isZero()
        );
    }

    @DisplayName("주말 할인 이벤트의 할인 금액을 정확히 계산한다.")
    @Test
    void calculateWeekendEventDiscountValue() {
        // given
        EventDate weekdayEventDate = EventDateFixture.디데이O_별X_평일_4일.create();
        EventDate weekendEventDate = EventDateFixture.디데이O_별X_주말_1일.create();
        Orders orders = OrdersAmountFixture.메인_바비큐립_주문.create(1);

        Plan weekdayPlan = new Plan(weekdayEventDate, orders);
        Plan weekendPlan = new Plan(weekendEventDate, orders);

        WeekdayEvent weekdayEvent = new WeekdayEvent("평일 할인");
        WeekendEvent weekendEvent = new WeekendEvent("주말 할인");

        // when
        int weekdayPlanDiscountValue = weekdayEvent.discount(weekdayPlan, 10000);
        int weekendPlanDiscountValue = weekendEvent.discount(weekendPlan, 10000);

        // then
        assertAll(
                () -> assertThat(weekdayPlanDiscountValue).isZero(),
                () -> assertThat(weekendPlanDiscountValue).isEqualTo(2023)
        );
    }

    @DisplayName("특별 할인 이벤트의 할인 금액을 정확히 계산한다.")
    @Test
    void calculateStarDayEventDiscountValue() {
        // given
        EventDate starDate = EventDateFixture.디데이O_별O_평일_3일.create();
        EventDate noStarDate = EventDateFixture.디데이O_별X_주말_1일.create();
        Orders orders = OrdersFixture.메인_주문_12만원_이하.create();

        Plan starDayPlan = new Plan(starDate, orders);
        Plan noStarDayPlan = new Plan(noStarDate, orders);

        StarDayEvent starDayEvent = new StarDayEvent("특별 할인");

        // when
        int starDayPlanDiscountValue = starDayEvent.discount(starDayPlan, 10000);
        int noStarDayPlanDiscountValue = starDayEvent.discount(noStarDayPlan, 100000);

        // then
        assertAll(
                () -> assertThat(starDayPlanDiscountValue).isEqualTo(1000),
                () -> assertThat(noStarDayPlanDiscountValue).isZero()
        );
    }

    @DisplayName("증정 이벤트의 할인 금액을 정확히 계산한다.")
    @Test
    void calculateGiveawayEventDiscountValue() {
        // given
        EventDate eventDate = EventDateFixture.디데이O_별O_평일_3일.create();

        Plan giveawayPlan = new Plan(
                eventDate,
                OrdersFixture.메인_주문_12만원_이상.create()
        );

        Plan noGiveawayPlan = new Plan(
                eventDate,
                OrdersFixture.메인_주문_12만원_이하.create()
        );

        DecemberGiveawayEvent event = new DecemberGiveawayEvent();

        // when
        int giveawayPlanDiscountValue = event.discount(giveawayPlan, 10000);
        int noGiveawayPlanDiscountValue = event.discount(noGiveawayPlan, 10000);

        // then
        assertAll(
                () -> assertThat(giveawayPlanDiscountValue).isEqualTo(25000),
                () -> assertThat(noGiveawayPlanDiscountValue).isZero()
        );
    }
}
