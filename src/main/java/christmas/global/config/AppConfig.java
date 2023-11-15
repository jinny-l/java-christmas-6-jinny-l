package christmas.global.config;

import christmas.date.repository.EventDateRepository;
import christmas.date.service.EventDateService;
import christmas.order.service.OrderService;
import christmas.payment.service.PaymentService;

public final class AppConfig {

    private static final AppConfig appConfig = new AppConfig();

    private AppConfig() {
    }

    public static AppConfig getInstance() {
        return appConfig;
    }

    public EventDateRepository eventDateRepository() {
        return EventDateRepository.getInstance();
    }

    public EventDateService eventDateService() {
        return EventDateService.getInstance();
    }

    public OrderService orderService() {
        return OrderService.getInstance();
    }

    public PaymentService paymentService() {
        return PaymentService.getInstance();
    }
}
