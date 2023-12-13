package christmas.global.config;

import christmas.benefit.service.BenefitService;
import christmas.date.service.EventDateService;

public final class AppConfig {

    private static final AppConfig appConfig = new AppConfig();

    private AppConfig() {
    }

    public static AppConfig getInstance() {
        return appConfig;
    }

    public BenefitService benefitService() {
        return BenefitService.getInstance();
    }

    public EventDateService eventDateService() {
        return EventDateService.getInstance();
    }

}
