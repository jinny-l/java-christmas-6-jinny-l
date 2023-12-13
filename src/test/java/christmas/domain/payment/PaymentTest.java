package christmas.domain.payment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import christmas.benefit.domain.Benefit;
import christmas.benefit.domain.Benefits;
import christmas.event.domain.Event;
import christmas.event.repository.EventRepository;
import christmas.fixture.EventDateFixture;
import christmas.fixture.OrdersAmountFixture;
import christmas.order.domain.Orders;
import christmas.payment.domain.Payment;
import christmas.plan.domain.Plan;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[단위 테스트][Domain] Payment")
public class PaymentTest {

    @DisplayName("증정 이벤트에 해당되는 경우 혜택 금액과 최종 결제 금액을 정확히 계산한다.")
    @Test
    void createPayment_haveGiveaway() {
        // given
        Orders orders = OrdersAmountFixture.메인_바비큐립_주문.create(3);
        Plan plan = new Plan(
                EventDateFixture.디데이X_별X_주말.create(),
                orders
        );
        Benefits benefits = createBenefits(plan);


        // when
        Payment actual = Payment.from(orders, benefits);

        // then
        assertAll(
                () -> assertThat(actual.discountValue()).isEqualTo(25000 + 2023 * 3),
                () -> assertThat(actual.finalValue()).isEqualTo( 54000 * 3 - 2023 * 3)
        );
    }

    @DisplayName("증정 이벤트에 해당되지 않는 경우 혜택 금액과 최종 결제 금액을 정확히 계산한다.")
    @Test
    void createPayment_noGiveaway() {
        // given
        Orders orders = OrdersAmountFixture.메인_바비큐립_주문.create(1);
        Plan plan = new Plan(
                EventDateFixture.디데이X_별X_주말.create(),
                orders
        );
        Benefits benefits = createBenefits(plan);


        // when
        Payment actual = Payment.from(orders, benefits);

        // then
        assertAll(
                () -> assertThat(actual.discountValue()).isEqualTo(2023),
                () -> assertThat(actual.finalValue()).isEqualTo( 54000 - 2023)
        );
    }

    private Benefits createBenefits(Plan plan) { // Service 쪽 코드 그대로 복붙한거라 나중에 리팩토링 필요
        List<Event> events = EventRepository.findAll();

        List<Benefit> benefits = events.stream()
                .map(event -> Benefit.from(event, plan))
                .toList();

        return new Benefits(benefits);
    }
}
