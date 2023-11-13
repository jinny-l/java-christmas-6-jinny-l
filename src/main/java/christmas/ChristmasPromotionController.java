package christmas;

import christmas.benefit.domain.Benefits;
import christmas.date.domain.EventDate;
import christmas.date.dto.VisitDayRequest;
import christmas.date.service.EventDateService;
import christmas.global.config.AppConfig;
import christmas.order.domain.Orders;
import christmas.order.dto.OrdersRequest;
import christmas.order.service.OrderService;
import christmas.payment.domain.Payment;
import christmas.payment.service.PaymentService;
import christmas.plan.domain.Plan;
import christmas.promotion.domain.Badge;
import christmas.promotion.domain.Promotion;
import christmas.promotion.dto.PromotionResponse;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasPromotionController {

    private final EventDateService eventDateService;
    private final OrderService orderService;
    private final PaymentService paymentService;

    public ChristmasPromotionController(AppConfig appConfig) {
        this.eventDateService = appConfig.eventDateService();
        this.orderService = appConfig.orderService();
        this.paymentService =  appConfig.paymentService();
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
            return orderService.createOrders(ordersRequest);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return readOrders();
        }
    }

    private Promotion createPromotion(Plan plan) {
        Benefits benefits = Benefits.from(plan);
        Payment payment = paymentService.createPayment(plan.orders(), benefits);
        Badge badge = Badge.from(payment.discountValue());

        return new Promotion(plan, benefits, badge, payment);
    }
}
