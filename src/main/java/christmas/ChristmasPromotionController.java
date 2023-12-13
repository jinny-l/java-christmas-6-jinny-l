package christmas;

import christmas.benefit.domain.Benefits;
import christmas.benefit.service.BenefitService;
import christmas.date.domain.EventDate;
import christmas.date.dto.VisitDayRequest;
import christmas.date.service.EventDateService;
import christmas.global.config.AppConfig;
import christmas.order.domain.Orders;
import christmas.order.dto.OrdersRequest;
import christmas.payment.domain.Payment;
import christmas.plan.domain.Plan;
import christmas.promotion.domain.Badge;
import christmas.promotion.domain.Promotion;
import christmas.promotion.dto.PromotionResponse;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasPromotionController {

    private final EventDateService eventDateService;
    private final BenefitService benefitService;

    public ChristmasPromotionController(AppConfig appConfig) {
        this.eventDateService = appConfig.eventDateService();
        this.benefitService = appConfig.benefitService();
    }

    public void run() {
        OutputView.printGreetingMessage();

        Plan plan = readAndCreatePlan();

        Promotion promotion = createPromotion(plan);
        OutputView.printPromotion(PromotionResponse.from(promotion));
    }

    private Plan readAndCreatePlan() {
        return new Plan(
                readVisitDay(),
                readOrders()
        );
    }

    private EventDate readVisitDay() {
        try {
            VisitDayRequest visitDayRequest = VisitDayRequest.from(InputView.readVisitDay());
            return eventDateService.findEventDay(visitDayRequest.dayOfMonth());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return readVisitDay();
        }
    }

    private Orders readOrders() {
        try {
            OrdersRequest ordersRequest = OrdersRequest.from(InputView.readOrders());
            return ordersRequest.toEntity();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return readOrders();
        }
    }

    private Promotion createPromotion(Plan plan) {
        Benefits benefits = benefitService.calculateBenefit(plan);
        Payment payment = Payment.from(plan.orders(), benefits);
        Badge badge = Badge.from(payment.discountValue());

        return new Promotion(plan, benefits, badge, payment);
    }
}
