package christmas.benefit.service;

import christmas.benefit.domain.Benefits;
import christmas.event.domain.Event;
import christmas.event.repository.EventRepository;
import christmas.plan.domain.Plan;
import java.util.List;

public class BenefitService {

    private static final BenefitService INSTANCE = new BenefitService();

    private BenefitService() {
    }

    public static BenefitService getInstance() {
        return INSTANCE;
    }

    public Benefits calculateBenefit(Plan plan) {
        List<Event> events = EventRepository.findAll();

        return Benefits.from(plan, events);
    }
}
