package christmas.global.config;

import christmas.date.repository.EventDateRepository;
import christmas.date.service.EventDateService;
import christmas.order.service.OrderService;
import christmas.payment.service.PaymentService;

public final class AppConfig {

    private static AppConfig appConfig = new AppConfig();

    private AppConfig() {
    }

    public static AppConfig getInstance() {
        return appConfig;
    }

    public EventDateRepository eventDateRepository() {
        return new EventDateRepository();
    }

    public EventDateService eventDateService() {
        return new EventDateService(eventDateRepository());
    }

    public OrderService orderService() {
        return new OrderService();
    }

    public PaymentService paymentService() {
        return new PaymentService();
    }
}
