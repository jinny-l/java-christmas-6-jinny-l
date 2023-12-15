package christmas.benefit.service;

import christmas.benefit.domain.Benefits;
import christmas.event.domain.Event;
import christmas.event.repository.EventRepository;
import christmas.order.domain.Menu;
import christmas.plan.domain.Plan;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public Map<Menu, Integer> getGiveaways(Plan plan) {
        return EventRepository.findGiveawayEvent().stream()
                .map(giveawayEvent -> giveawayEvent.getGiveaways(plan))
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue)
                );
    }
}
