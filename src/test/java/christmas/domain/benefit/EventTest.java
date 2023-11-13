package christmas.domain.benefit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import christmas.benefit.domain.Event;
import christmas.date.domain.EventDate;
import christmas.fixture.EventDateFixture;
import christmas.order.domain.Menu;
import christmas.order.domain.Order;
import christmas.order.domain.Orders;
import christmas.plan.domain.Plan;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[단위 테스트][Domain] Event")
class EventTest {

    @DisplayName("크리스마스 디데이 할인 이벤트에 해당할 경우 할인 금액을 정확히 계산한다.")
    @Test
    void calculateDDayEventDiscountAmount_isDDayEventDate() {
        // given
        EventDate eventDate = EventDateFixture.디데이O_별X_평일.create();
        Plan plan = new Plan(
                eventDate,
                new Orders(List.of(
                        new Order(Menu.CHOCO_CAKE, 1),
                        new Order(Menu.BBQ_RIBS, 19)))
        );

        // when
        int actual = Event.D_DAY.calculateDiscountValue(plan);

        // then
        assertThat(actual).isEqualTo(eventDate.discountAmount());
    }

    @DisplayName("크리스마스 디데이 할인 이벤트에 해당하지 않을 경우 할인 금액을 정확히 계산한다.")
    @Test
    void calculateDDayEventDiscountAmount_isNotDDayEventDate() {
        // given
        EventDate eventDate = EventDateFixture.디데이X_별X_평일.create();
        Plan plan = new Plan(
                eventDate,
                new Orders(List.of(
                        new Order(Menu.CHOCO_CAKE, 1),
                        new Order(Menu.BBQ_RIBS, 19)))
        );

        // when
        int actual = Event.D_DAY.calculateDiscountValue(plan);

        // then
        assertThat(actual).isZero();
    }

    @DisplayName("평일 할인 이벤트의 할인 금액을 정확히 계산한다.")
    @Test
    void calculateWeekdayEventDiscountAmount() {
        // given
        EventDate weekdayEventDate = EventDateFixture.디데이O_별X_평일.create();
        Plan weekdayPlan = new Plan(
                weekdayEventDate,
                new Orders(List.of(
                        new Order(Menu.CHOCO_CAKE, 1),
                        new Order(Menu.BBQ_RIBS, 1)))
        );

        EventDate weekendEventDate = EventDateFixture.디데이O_별X_주말.create();
        Plan weekendPlan = new Plan(
                weekendEventDate,
                new Orders(List.of(
                        new Order(Menu.CHOCO_CAKE, 1),
                        new Order(Menu.BBQ_RIBS, 1)))
        );

        // when
        int weekdayPlanDiscountAmount = Event.WEEKDAY.calculateDiscountValue(weekdayPlan);
        int weekendPlanDiscountAmount = Event.WEEKDAY.calculateDiscountValue(weekendPlan);

        // then
        assertAll(
                () -> assertThat(weekdayPlanDiscountAmount).isEqualTo(2023),
                () -> assertThat(weekendPlanDiscountAmount).isZero()
        );
    }

    @DisplayName("주말 할인 이벤트의 할인 금액을 정확히 계산한다.")
    @Test
    void calculateWeekendEventDiscountAmount() {
        // given
        EventDate weekdayEventDate = EventDateFixture.디데이O_별X_평일.create();
        Plan weekdayPlan = new Plan(
                weekdayEventDate,
                new Orders(Collections.singletonList(
                        new Order(Menu.BBQ_RIBS, 1)))
        );

        EventDate weekendEventDate = EventDateFixture.디데이O_별X_주말.create();
        Plan weekendPlan = new Plan(
                weekendEventDate,
                new Orders(Collections.singletonList(
                        new Order(Menu.BBQ_RIBS, 1)))
        );

        // when
        int weekdayPlanDiscountAmount = Event.WEEKEND.calculateDiscountValue(weekdayPlan);
        int weekendPlanDiscountAmount = Event.WEEKEND.calculateDiscountValue(weekendPlan);

        // then
        assertAll(
                () -> assertThat(weekdayPlanDiscountAmount).isZero(),
                () -> assertThat(weekendPlanDiscountAmount).isEqualTo(2023)
        );
    }

    @DisplayName("특별 할인 이벤트의 할인 금액을 정확히 계산한다.")
    @Test
    void calculateStarDayEventDiscountAmount() {
        // given
        EventDate starDate = EventDateFixture.디데이O_별O_평일.create();
        Plan starDayPlan = new Plan(
                starDate,
                new Orders(Collections.singletonList(
                        new Order(Menu.BBQ_RIBS, 1)))
        );

        EventDate noStarDate = EventDateFixture.디데이O_별X_주말.create();
        Plan noStarDayPlan = new Plan(
                noStarDate,
                new Orders(Collections.singletonList(
                        new Order(Menu.BBQ_RIBS, 1)))
        );

        // when
        int starDayPlanDiscountAmount = Event.STAR_DAY.calculateDiscountValue(starDayPlan);
        int noStarDayPlanDiscountAmount = Event.STAR_DAY.calculateDiscountValue(noStarDayPlan);

        // then
        assertAll(
                () -> assertThat(starDayPlanDiscountAmount).isEqualTo(1000),
                () -> assertThat(noStarDayPlanDiscountAmount).isZero()
        );
    }

    @DisplayName("증정 이벤트의 할인 금액을 정확히 계산한다.")
    @Test
    void test() {
        // given
        EventDate eventDate = EventDateFixture.디데이O_별O_평일.create();
        Plan giveawayPlan = new Plan(
                eventDate,
                new Orders(Collections.singletonList(
                        new Order(Menu.BBQ_RIBS, 3)))
        );

        Plan noGiveawayPlan = new Plan(
                eventDate,
                new Orders(Collections.singletonList(
                        new Order(Menu.BBQ_RIBS, 1)))
        );

        // when
        int giveawayPlanDiscountAmount = Event.GIVEAWAY.calculateDiscountValue(giveawayPlan);
        int noGiveawayPlanDiscountAmount = Event.GIVEAWAY.calculateDiscountValue(noGiveawayPlan);

        // then
        assertAll(
                () -> assertThat(giveawayPlanDiscountAmount).isEqualTo(25000),
                () -> assertThat(noGiveawayPlanDiscountAmount).isZero()
        );
    }
}
