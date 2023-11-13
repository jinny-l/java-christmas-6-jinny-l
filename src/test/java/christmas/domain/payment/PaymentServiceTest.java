package christmas.domain.payment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import christmas.benefit.domain.Benefits;
import christmas.fixture.EventDateFixture;
import christmas.fixture.OrdersAmountFixture;
import christmas.global.config.AppConfig;
import christmas.order.domain.Orders;
import christmas.payment.domain.Payment;
import christmas.payment.service.PaymentService;
import christmas.plan.domain.Plan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[단위 테스트][Service] PaymentService")
public class PaymentServiceTest {

    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        paymentService = AppConfig.getInstance().paymentService();
    }

    @DisplayName("증정 이벤트에 해당되는 경우 혜택 금액과 최종 결제 금액을 정확히 계산한다.")
    @Test
    void createPayment_haveGiveaway() {
        // given
        Orders orders = OrdersAmountFixture.메인_바비큐립_주문.create(3);
        Plan plan = new Plan(
                EventDateFixture.디데이X_별X_주말.create(),
                orders
        );
        Benefits benefits = Benefits.from(plan);


        // when
        Payment actual = paymentService.createPayment(orders, benefits);

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
        Benefits benefits = Benefits.from(plan);


        // when
        Payment actual = paymentService.createPayment(orders, benefits);

        // then
        assertAll(
                () -> assertThat(actual.discountValue()).isEqualTo(2023),
                () -> assertThat(actual.finalValue()).isEqualTo( 54000 - 2023)
        );
    }
}
